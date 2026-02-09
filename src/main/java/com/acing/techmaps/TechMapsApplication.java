package com.acing.techmaps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class TechMapsApplication {
    public static void main(String[] args) {
        SpringApplication.run(TechMapsApplication.class, args);
    }

    @Value("${spring.servlet.multipart.max-request-size:UNSET}")
    private String maxRequestSize;

    @Value("${spring.servlet.multipart.max-file-size:UNSET}")
    private String maxFileSize;

    @PostConstruct
    public void printConfig() {
        System.out.println("========================================");
        System.out.println("DEBUG: Max File Size: " + maxFileSize);
        System.out.println("DEBUG: Max Request Size: " + maxRequestSize);
        System.out.println("========================================");
    }

}
