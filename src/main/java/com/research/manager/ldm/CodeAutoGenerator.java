package com.research.manager.ldm;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.Collections;

public class CodeAutoGenerator {

    private static final String URL = "jdbc:mysql://localhost:3306/tesb_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "weihao0917";

    /**
     * 自动生成代码输出目录，这里默认类路径下的src/main/java包下。
     */
    private static final String OUTPUT_DIR = System.getProperty("user.dir") + File.separator + "src/main/java";

    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                // 开启fileOverrride重新旧文件，disableOpenDir禁用代码生成后打开输出目录对话框
                .globalConfig(builder -> builder.author("weihao").disableOpenDir().outputDir(OUTPUT_DIR).dateType(DateType.ONLY_DATE))
                // parent指定生成的代码在哪个包下，entity可以指定实体(DO)所在的包名
                .packageConfig(builder ->builder.parent("com.research.manager.ldm")
                            .entity("entity")
                            .controller("controller")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            //配置xml的路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + File.separator + "src/main/resources/mapper"))
                )
                // addInclude指定包含的表名，不调用该方法默认为所有表生成代码；addTablePrefix可以过滤表前缀，即t_user变成user
                .strategyConfig(builder ->
                    builder.addInclude("sys_user").addTablePrefix("sys_")
                                    // 禁用为实体类生成序列化ID；formatFileName格式化生成的实体类名称，即t_user -> UserDO
                                    .entityBuilder().enableLombok().naming(NamingStrategy.underline_to_camel).columnNaming(NamingStrategy.underline_to_camel).idType(IdType.AUTO).formatFileName("%s")
                                    // formatMapperFileName格式化Mapper接口名称，即t_user -> UserMapper
                                    // formatXmlFileName格式化Mapper.xml文件名称，即t_user -> UserMapper.xml
                                    .mapperBuilder().enableBaseResultMap().enableBaseColumnList().formatMapperFileName("%sMapper").formatXmlFileName("%sMapper")
                                    .serviceBuilder().formatServiceFileName("%sService").formatServiceImplFileName("%ServiceImpl").controllerBuilder().enableRestStyle()
                )
                // MyBatis-Plus代码生成器是通过模板引擎来渲染文件的，默认模板引擎是Velocity，根据依赖我们使用Freemarker
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
