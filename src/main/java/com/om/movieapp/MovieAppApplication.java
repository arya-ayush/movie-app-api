package com.om.movieapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@MapperScan("com.om.movieapp.dao")
public class MovieAppApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(MovieAppApplication.class, args);
  }


}
