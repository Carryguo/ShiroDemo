package com.carry.shirodemo2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@MapperScan(basePackages = {"com.carry.shirodemo2.mapper"})
@SpringBootApplication
public class Shirodemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Shirodemo2Application.class, args);
    }

}
