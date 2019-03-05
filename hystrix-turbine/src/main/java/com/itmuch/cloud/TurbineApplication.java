package com.itmuch.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
//import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @Auth wn
 * @Date 2019/2/28
 */
@SpringBootApplication
//@EnableTurbine
@EnableTurbineStream
public class TurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurbineApplication.class);
    }
}
