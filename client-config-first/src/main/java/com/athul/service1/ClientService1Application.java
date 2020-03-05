package com.athul.service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ClientService1Application {

    public static void main(String[] args) {
        SpringApplication.run(ClientService1Application.class, args);
    }

}
