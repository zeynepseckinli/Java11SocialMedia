package com.zeynep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
    7071'de ayağa kalkan bir user-service modulü oluşturalım.

    Postgre bağlantılarını da gerçekleştirelim.
        -> auth-service icin : java11AuthDB
        -> user-service icin : java11UserDB
 */
@SpringBootApplication
@EnableFeignClients
public class AuthServiceApplication {
  public static void main(String[] args){
      SpringApplication.run(AuthServiceApplication.class);
  }
}