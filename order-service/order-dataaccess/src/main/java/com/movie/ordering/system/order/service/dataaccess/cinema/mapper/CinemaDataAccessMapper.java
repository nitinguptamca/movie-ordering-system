package com.movie.ordering.system.order.service.dataaccess.cinema.mapper;

import com.movie.ordering.system.dataaccess.cinema.entity.CinemaEntity;
import com.movie.ordering.system.domain.valueobject.CinemaId;
import com.movie.ordering.system.domain.valueobject.Money;
import com.movie.ordering.system.domain.valueobject.MovieId;
import com.movie.ordering.system.order.service.domain.entity.Cinema;
import com.movie.ordering.system.order.service.domain.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CinemaDataAccessMapper {

    public List<UUID> cinemaToCinemaMovies(Cinema cinema) {
        return cinema.getMovies().stream()
                .map(movie -> movie.getId().getValue())
                .collect(Collectors.toList());
    }

    public Cinema cinemaEntityToCinema(List<CinemaEntity> cinemaEntitiesEntities) {
        com.movie.ordering.system.dataaccess.cinema.entity.CinemaEntity CinemaEntity =
                cinemaEntitiesEntities.stream().findFirst().orElseThrow(() ->
                        new com.movie.ordering.system.dataaccess.cinema.exception.CinemaDataAccessException("Cinema could not be found!"));

        List<Movie> CinemaMovies = cinemaEntitiesEntities.stream().map(entity ->
                new Movie(new MovieId(entity.getMovieId()), entity.getMovieName(),
                        new Money(entity.getMoviePrice()))).toList();

        return Cinema.builder()
                .cinemaId(new CinemaId(CinemaEntity.getCinemaId()))
                .movies(CinemaMovies)
                .active(CinemaEntity.getCinemaActive())
                .build();
    }
}
