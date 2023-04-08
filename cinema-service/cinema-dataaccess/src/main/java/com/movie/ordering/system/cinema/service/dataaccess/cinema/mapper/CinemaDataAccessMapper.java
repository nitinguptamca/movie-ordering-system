package com.movie.ordering.system.cinema.service.dataaccess.cinema.mapper;

import com.movie.ordering.system.cinema.service.dataaccess.cinema.entity.OrderApprovalEntity;
import com.movie.ordering.system.cinema.service.domain.entity.Cinema;
import com.movie.ordering.system.cinema.service.domain.entity.Movie;
import com.movie.ordering.system.cinema.service.domain.entity.OrderApproval;
import com.movie.ordering.system.cinema.service.domain.entity.OrderDetail;
import com.movie.ordering.system.cinema.service.domain.valueobject.OrderApprovalId;
import com.movie.ordering.system.dataaccess.cinema.entity.CinemaEntity;
import com.movie.ordering.system.domain.valueobject.CinemaId;
import com.movie.ordering.system.domain.valueobject.Money;
import com.movie.ordering.system.domain.valueobject.MovieId;
import com.movie.ordering.system.domain.valueobject.OrderId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CinemaDataAccessMapper {

    public List<UUID> cinemaToCinemaMovies(Cinema cinema) {
        return cinema.getOrderDetail().getMovies().stream()
                .map(movie -> movie.getId().getValue())
                .collect(Collectors.toList());
    }

    public Cinema cinemaEntityToCinema(List<CinemaEntity> cinemaEntities) {
        com.movie.ordering.system.dataaccess.cinema.entity.CinemaEntity cinemaEntity =
                cinemaEntities.stream().findFirst().orElseThrow(() ->
                        new com.movie.ordering.system.dataaccess.cinema.exception.CinemaDataAccessException("No Cinema found!"));

        List<Movie> cinemaMovies = cinemaEntities.stream().map(entity ->
                        Movie.builder()
                                .movieId(new MovieId(entity.getMovieId()))
                                .name(entity.getMovieName())
                                .price(new Money(entity.getMoviePrice()))
                                .available(entity.getMovieAvailable())
                                .build())
                .collect(Collectors.toList());

        return Cinema.builder()
                .cinemaId(new CinemaId(cinemaEntity.getCinemaId()))
                .orderDetail(OrderDetail.builder()
                        .movies(cinemaMovies)
                        .build())
                .active(cinemaEntity.getCinemaActive())
                .build();
    }

    public OrderApprovalEntity orderApprovalToOrderApprovalEntity(OrderApproval orderApproval) {
        return OrderApprovalEntity.builder()
                .id(orderApproval.getId().getValue())
                .cinemaId(orderApproval.getCinemaId().getValue())
                .orderId(orderApproval.getOrderId().getValue())
                .status(orderApproval.getApprovalStatus())
                .build();
    }

    public OrderApproval orderApprovalEntityToOrderApproval(OrderApprovalEntity orderApprovalEntity) {
        return OrderApproval.builder()
                .orderApprovalId(new OrderApprovalId(orderApprovalEntity.getId()))
                .cinemaId(new CinemaId(orderApprovalEntity.getCinemaId()))
                .orderId(new OrderId(orderApprovalEntity.getOrderId()))
                .approvalStatus(orderApprovalEntity.getStatus())
                .build();
    }

}
