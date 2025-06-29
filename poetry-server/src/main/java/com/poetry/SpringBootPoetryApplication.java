package com.poetry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author moZiA
 */
@SpringBootApplication
@MapperScan("com.poetry.mapper")
@EnableScheduling
@EnableAsync
public class SpringBootPoetryApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootPoetryApplication.class, args);
  }

}