package com.ibolsa.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ibolsa.api.repositories.pg")
@EnableMongoRepositories(basePackages = "com.ibolsa.api.repositories.mongo")
public class IbolsaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IbolsaApiApplication.class, args);
    }

}
