package com.template.simple;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@MapperScan("com.template.simple.mapper")
public class Java8SpringSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(Java8SpringSimpleApplication.class, args);
    }

}
