package com.rodrigo.msoauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class MsOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsOauthApplication.class, args);
    }

}
