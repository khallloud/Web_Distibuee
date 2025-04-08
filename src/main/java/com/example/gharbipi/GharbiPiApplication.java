package com.example.gharbipi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GharbiPiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GharbiPiApplication.class, args);
    }

}
