package com.karen.mesr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(value = "com.karen.mesr.mapper")
//@ComponentScan(value = "com.karen.mesr.service")
@SpringBootApplication
public class SpringBootMesRApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMesRApplication.class, args);
    }

}
