package scorpio.core.util.excel;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 員工 excel 表格
 */
@Getter
@Setter

public class EmployeeModel  {
    @Excel(name = "姓名", index = 0)
    private String name;
    @Excel(name = "員編", index = 1)
    private String employeeId;
    @Excel(name = "帳號", index = 2)
    private String username;
    @Excel(name = "密碼", index = 3)
    private String password;
    @Excel(name = "性別", index = 4, remark = "0:female，1:male，2:other")
    private Integer gender;
    @Excel(name = "生日", index = 5)
    private Date birthday;
    @Excel(name = "email", index = 6)
    private String email;
    @Excel(name = "任職狀態", index = 7, remark = "1:probation，2:full-time，3:contract，4:departure")
    private Integer status;
    @Excel(name = "暱稱", index = 8)
    private String nickName;
    @Excel(name = "部門名稱", index = 9)
    private String deptName;
    @Excel(name = "職稱", index = 10)
    private String title;
}
