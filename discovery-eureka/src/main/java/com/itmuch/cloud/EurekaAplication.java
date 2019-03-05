package com.itmuch.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Auth wn
 * @Date 2019/2/25
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaAplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaAplication.class, args);
    }
}
