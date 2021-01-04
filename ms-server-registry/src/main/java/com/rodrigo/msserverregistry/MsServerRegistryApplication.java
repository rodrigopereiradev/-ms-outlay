package com.rodrigo.msserverregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MsServerRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsServerRegistryApplication.class, args);
    }

}
