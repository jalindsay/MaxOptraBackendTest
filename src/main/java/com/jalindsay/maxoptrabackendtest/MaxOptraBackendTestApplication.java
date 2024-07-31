package com.jalindsay.maxoptrabackendtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MaxOptraBackendTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaxOptraBackendTestApplication.class, args);
    }

}
