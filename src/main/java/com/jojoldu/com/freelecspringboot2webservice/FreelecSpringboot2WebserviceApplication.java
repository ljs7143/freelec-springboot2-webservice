package com.jojoldu.com.freelecspringboot2webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class FreelecSpringboot2WebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreelecSpringboot2WebserviceApplication.class, args);
    }


}
