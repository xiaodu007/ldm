package com.research.manager.sysmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.research.manager.*"})
@MapperScan("com.research.manager.sysmanager.mapper")
@EnableDiscoveryClient
public class SysmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysmanagerApplication.class, args);
    }

}
