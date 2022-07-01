package com.om.movieapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("com.om.movieapp.dao")
public class MovieAppApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(MovieAppApplication.class, args);
  }
}
