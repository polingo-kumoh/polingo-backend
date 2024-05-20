package com.tangtang.polingo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
public class PolingoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolingoApplication.class, args);
    }
}
