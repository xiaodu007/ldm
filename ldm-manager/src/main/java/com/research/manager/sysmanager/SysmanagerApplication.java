package com.research.manager.sysmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.research.manager.*"})
@MapperScan("com.research.manager.sysmanager.mapper")
public class SysmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysmanagerApplication.class, args);
    }

}
