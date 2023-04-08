package com.movie.ordering.system.cinema.service.domain;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.movie.ordering.system.cinema.service.dataaccess", "com.movie.ordering.system.dataaccess"})
@EntityScan(basePackages = {"com.movie.ordering.system.cinema.service.dataaccess", "com.movie.ordering.system.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.movie.ordering.system")
public class CinemaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CinemaServiceApplication.class, args);
    }
}
