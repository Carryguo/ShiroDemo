package com.carry.shirodemo3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@MapperScan(basePackages = {"com.carry.shirodemo3.mapper"})
@SpringBootApplication
public class Shirodemo3Application {

    public static void main(String[] args) {
        SpringApplication.run(Shirodemo3Application.class, args);
    }

}
