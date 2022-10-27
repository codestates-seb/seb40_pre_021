package com.pre21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PreProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreProjectApplication.class, args);
    }

}
