package com.example.testovoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TestovoeApplication {

  public static void main(String[] args) {
    SpringApplication.run(TestovoeApplication.class, args);
  }

}
