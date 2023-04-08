package com.movie.ordering.system.cinema.service.domain.dto;


import com.movie.ordering.system.cinema.service.domain.entity.Movie;
import com.movie.ordering.system.domain.valueobject.CinemaOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CinemaApprovalRequest {
    private String id;
    private String sagaId;
    private String cinemaId;
    private String orderId;
    private CinemaOrderStatus cinemaOrderStatus ;
    private java.util.List<Movie> movies;
    private java.math.BigDecimal price;
    private java.time.Instant createdAt;
}
