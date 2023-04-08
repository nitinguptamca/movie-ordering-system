package com.movie.ordering.system.cinema.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CinemaDomainService cinemaDomainService() {
        return new CinemaDomainServiceImpl();
    }

}
