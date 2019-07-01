package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CloudJdbcDeviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudJdbcDeviceApplication.class, args);
    }

}
