package com.movie.ordering.system.cinema.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "cinema-service")
public class CinemaServiceConfigData {
    private String cinemaApprovalRequestTopicName;
    private String cinemaApprovalResponseTopicName;
}
