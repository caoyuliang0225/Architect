package com.cloud.architect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 自动生成 createDate
@SpringBootApplication
public class ArchitectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchitectApplication.class, args);
    }

}
