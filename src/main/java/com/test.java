package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
@MapperScan(basePackages = {"com.mapper"})
public class test {
    public static void main(String args[]){
        SpringApplication.run(test.class , args);
    }
}