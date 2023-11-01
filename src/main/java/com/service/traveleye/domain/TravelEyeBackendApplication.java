package com.service.traveleye.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TravelEyeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelEyeBackendApplication.class, args);
    }

}
