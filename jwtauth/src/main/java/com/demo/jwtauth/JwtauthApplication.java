package com.demo.jwtauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = {"com.demo"})
public class JwtauthApplication {

    public static void main(String[] args) {
        //设置时区为 GMT+8
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SpringApplication.run(JwtauthApplication.class, args);
    }

}
