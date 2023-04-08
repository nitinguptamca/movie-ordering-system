package com.movie.ordering.system.order.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.movie.ordering.system.order.service.dataaccess", "com.movie.ordering.system.dataaccess"})
@EntityScan(basePackages = {"com.movie.ordering.system.order.service.dataaccess", "com.movie.ordering.system.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.movie.ordering.system")
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
