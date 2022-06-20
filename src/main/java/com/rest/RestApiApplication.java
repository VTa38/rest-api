package com.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RestApiApplication.class, args);
    }

}
