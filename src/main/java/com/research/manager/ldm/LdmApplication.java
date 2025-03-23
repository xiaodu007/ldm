package com.research.manager.ldm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.research.manager.ldm.mapper")
public class LdmApplication {

	public static void main(String[] args) {
		SpringApplication.run(LdmApplication.class, args);
	}

}
