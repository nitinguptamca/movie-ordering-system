package com.movie.ordering.system.cinema.service.domain.mapper;

import com.movie.ordering.system.cinema.service.domain.dto.CinemaApprovalRequest;
import com.movie.ordering.system.cinema.service.domain.entity.Cinema;
import com.movie.ordering.system.cinema.service.domain.entity.Movie;
import com.movie.ordering.system.cinema.service.domain.entity.OrderDetail;
import com.movie.ordering.system.domain.valueobject.CinemaId;
import com.movie.ordering.system.domain.valueobject.Money;
import com.movie.ordering.system.domain.valueobject.OrderId;
import com.movie.ordering.system.domain.valueobject.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CinemaDataMapper {
    public Cinema cinemaApprovalRequestToCinema(CinemaApprovalRequest
                                                                cinemaApprovalRequest) {
        return Cinema.builder()
                .cinemaId(new CinemaId(UUID.fromString(cinemaApprovalRequest.getCinemaId())))
                .orderDetail(OrderDetail.builder()
                        .orderId(new OrderId(UUID.fromString(cinemaApprovalRequest.getOrderId())))
                        .movies(cinemaApprovalRequest.getMovies().stream().map(
                                        movie -> Movie.builder()
                                                .movieId(movie.getId())
                                                .quantity(movie.getQuantity())
                                                .build())
                                .collect(Collectors.toList()))
                        .totalAmount(new Money(cinemaApprovalRequest.getPrice()))
                        .orderStatus(OrderStatus.valueOf(cinemaApprovalRequest.getCinemaOrderStatus().name()))
                        .build())
                .build();
    }
}
