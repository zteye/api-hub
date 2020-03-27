package com.xxl.glue.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xxl.glue.admin.dao")
public class ApiHubAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiHubAdminApplication.class,args);
    }
}
