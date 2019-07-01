package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CloudJdbcUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudJdbcUserApplication.class, args);
    }

}
