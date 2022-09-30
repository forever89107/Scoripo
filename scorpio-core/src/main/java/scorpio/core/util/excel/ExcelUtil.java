package scorpio.core.util.excel;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scorpio.core.util.EmptyUtil;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class ExcelUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ExcelUtil.class);

    private static final String SET = "set";


    public static void main(String[] args) {
        String fileName = "static/匯入員工資訊.xlsx";
        Class<EmployeeModel> clz = EmployeeModel.class;

        InputStream stream = null;
        Workbook workbook = null;
        try {
            stream = ExcelUtil.class.getClassLoader().getResourceAsStream("static/匯入員工資訊.xlsx");
            assert stream != null;
            workbook = WorkbookFactory.create(stream);
            Sheet sheet = workbook.getSheet("員工");

            excelFileToObject(sheet, clz);

        } catch (Exception e) {
            LOG.warn("解析Excel失敗，文件名：" + fileName + " Error Msg：" + e.getMessage());
        } finally {
            try {
                if (null != workbook) workbook.close();
                if (null != stream) stream.close();
            } catch (Exception e) {
                LOG.warn("關閉stream 出錯！Error Msg：" + e.getMessage());
            }
        }
    }


    /**
     * excel sheet to Object
     *
     * @param sheet sheet
     * @param <T>   clz
     * @return List<T>
     * @throws ExcelException exception
     */
    public static <T> List<T> excelFileToObject(Sheet sheet, Class<T> clz) throws ExcelException {
        //key -> column index, value -> column setter function
        Map<Integer, String> methodMap = new HashMap<>();
        //key ->  column index, value -> column name
        Map<Integer, String> cloumnNameMap = new HashMap<>();
        //key ->  column index, value -> column is must
        Map<Integer, Boolean> fieldIsMustMap = new HashMap<>();
        //get class all fields
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            Excel excel = field.getAnnotation(Excel.class);
            if (excel == null) continue;
            String startName = field.getName().substring(0, 1);
            String endName = field.getName().substring(1);
            String methodName = SET + startName.toUpperCase() + endName;
            methodMap.put(excel.index(), methodName);
            cloumnNameMap.put(excel.index(), excel.name());
            fieldIsMustMap.put(excel.index(), excel.isMust());
        }
        // firstRow is column name not data
        Row firstRow = sheet.getRow(0);
        // check excel column
        for (Cell cell : firstRow) {
            Integer columnIndex = cell.getColumnIndex();
            if (cell.getCellType() != CellType.STRING)
                throw new ExcelException("excel校驗失敗, 請勿刪除檔案中第一行資料 !!!");
            String value = cell.getStringCellValue();
            String name = cloumnNameMap.get(columnIndex);
            if (name == null) throw new ExcelException("excel校驗失敗,請勿移動檔案中任何列的順序!!!");
            if (!name.equals(value)) throw new ExcelException("excel校驗失敗,【" + name + "】列被刪除,請勿刪除檔案中任何列 !!!");
        }
        sheet.removeRow(firstRow);
        List<T> models = new ArrayList<>();
        for (Row row : sheet) {
            if (row == null || !checkRow(row)) continue;
            try {
                // clz instance
                T obj = clz.getDeclaredConstructor().newInstance();
                Class<?> excelModelClass = obj.getClass();
                // according to cloumn name to build setter fun
                cloumnNameMap.forEach((index, value1) -> {
                    Cell cell = row.getCell(index);
                    String methodName = methodMap.get(index);
                    if (EmptyUtil.stringIsEmpty(methodName)) return;
                    List<Method> methods = Arrays.stream(excelModelClass.getMethods())
                            .filter(m -> m.getName().startsWith(SET)).collect(Collectors.toList());
                    // cloumn name
                    String rowName = cloumnNameMap.get(index);
                    for (Method method : methods) {
                        if (!method.getName().startsWith(methodName)) continue;
                        //檢測value屬性
                        String value = null;
                        try {
                            value = valueCheck(cell, rowName, fieldIsMustMap.get(index));
                        } catch (ExcelException e) {
                            e.printStackTrace();
                        }
                        //開始進行呼叫方法反射賦值
                        methodInvokeHandler(obj, method, value);
                    }
                });
                models.add(obj);

            } catch (InstantiationException | IllegalAccessException |
                    NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return models;
    }


    private static boolean checkRow(Row row) {
        try {
            if (row == null) return false;
            short firstCellNum = row.getFirstCellNum();
            short lastCellNum = row.getLastCellNum();
            if (firstCellNum < 0 && lastCellNum < 0) return false;
            if (firstCellNum != 0) {
                for (short i = firstCellNum; i < lastCellNum; i++) {
                    Cell cell = row.getCell(i);
                    String cellValue = cell.getStringCellValue();
                    if (EmptyUtil.stringIsNotEmpty(cellValue)) return true;
                }
                return false;
            }
            return true;
        } catch (Exception e) {
            return true;
        }

    }

    /**
     * 反射賦值的處理的方法
     *
     * @param obj    迴圈建立的需要賦值的物件
     * @param method 當前物件期中一個set方法
     * @param value  要被賦值的內容
     */
    private static void methodInvokeHandler(Object obj, Method method, String value) {
        Class<?> parameterType = method.getParameterTypes()[0];
        try {
            if (parameterType == null) {
                method.invoke(obj);
                return;
            }
            String name = parameterType.getName();
            if (name.equals(String.class.getName())) {
                method.invoke(obj, value);
                return;
            }
            if (name.equals(Long.class.getName())) {
                method.invoke(obj, Long.valueOf(value));
                return;
            }
            if (name.equals(Double.class.getName())) {
                method.invoke(obj, Double.valueOf(value));
            }

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 檢測當前需要賦值的value
     *
     * @param cell    當前迴圈行中的列物件
     * @param rowName 列的名字{@link Excel}中的name
     * @param isMust  是否為必須的
     * @return 值
     */
    private static String valueCheck(Cell cell, String rowName, Boolean isMust) throws ExcelException {
        //有時候刪除單個資料會造成cell為空,也可能是value為空
        if (cell == null && isMust) {
            throw new ExcelException("excel校驗失敗,【" + rowName + "】中的資料禁止單個刪除");
        }
        if (cell == null) return null;
        String value = new DataFormatter().formatCellValue(cell);

        if ((value == null || value.trim().isEmpty()) && isMust) {
            throw new ExcelException("excel校驗失敗,【" + rowName + "】中的資料禁止單個刪除");
        }
        return value;
    }

}
