import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;

public class MybatisGenerator {
    private static final String database = "my_side_db";
    private static final String module = "/scorpio-resource";
    private static final String packager = "com.my.resource.generator";
    private static final String jdbc = "jdbc:postgresql://localhost:5432/";
    private static final String username = "postgres";
    private static final String pwd = "postgresql";

    public static void main(String[] args) {
        //1、DB connection config
        FastAutoGenerator.create(jdbc + database, username, pwd)
                //2、Global config
                .globalConfig(builder -> builder.author("MBG") // 设置作者名
                        .outputDir(System.getProperty("user.dir") + module + "/src/main/java/") //output dir config
                        .commentDate("yyyy-MM-dd hh:mm:ss")   //時間戳記
                        .dateType(DateType.ONLY_DATE)   //TIME_PACK=LocalDateTime;ONLY_DATE=Date;
//                        .fileOverride()   //覆蓋之前的文件
//                        .enableSwagger()   //開啟 swagger 模式
                        .disableOpenDir() //禁止打開輸出目錄
                        .build())
                //3、package config
                .packageConfig(builder ->
                        builder.parent(packager) // parent package config
//                        .moduleName("scorpio-user")   //new package below  parent
                                .entity("entity")   //pojo entity
                                .service("service") //Service 包名
                                .serviceImpl("serviceImpl") // ***ServiceImpl 包名
                                .xml("mapperXML")  //Mapper XML 包名
                                .mapper("mapper")   //Mapper 包名
                                .controller("controller") //Controller 包名
                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + module + "/src/main/resources/mapper")) //指定某類型檔案生成至指定位置
                                .build())
                //4、strategy config
                .strategyConfig(builder -> builder.addInclude("user") // include table
                        .enableSkipView()// skip view gen
                        // Service Builder
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")

                        // Entity Builder
                        .entityBuilder()
                        // （重要）主鍵模式，配合mysql的自增主鍵 or 選擇none->自行使用java uuid
//                        .idType(IdType.AUTO)
                        // activeRecord模式，使用上来说就是可以直接在entity上执行insert、update等操作
                        .enableActiveRecord()
                        .enableLombok() //open lombok
//                        .disableSerialVersionUID()
//                        .logicDeleteColumnName("deleted")
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
//                            .addTableFills(new Column("create_time", FieldFill.INSERT), new Column("modify_time", FieldFill.INSERT_UPDATE))
                        .enableTableFieldAnnotation()

                        // Controller Builder
                        .controllerBuilder()
                        .formatFileName("%sController")
                        .enableRestStyle()

                        // Mapper Builder
                        .mapperBuilder()
                        .enableBaseColumnList()
                        .superClass(BaseMapper.class)
                        .formatMapperFileName("%sMapper")
                        .enableMapperAnnotation()       //@mapper
                        .formatXmlFileName("%sMapper")
                        .build())
                //5、template engine
                .templateEngine(new VelocityTemplateEngine())
                //6、execute
                .execute();
    }
}
