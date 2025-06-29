package com.poetry.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.poetry")
@EnableScheduling
@EnableAsync
public class SpringBootPoetryApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootPoetryApplication.class, args);
  }

}