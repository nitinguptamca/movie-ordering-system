package com.movie.ordering.system.order.service.dataaccess.cinema.adapter;


import com.movie.ordering.system.dataaccess.cinema.entity.CinemaEntity;
import com.movie.ordering.system.dataaccess.cinema.repository.CinemaJpaRepository;
import com.movie.ordering.system.order.service.dataaccess.cinema.mapper.CinemaDataAccessMapper;
import com.movie.ordering.system.order.service.domain.entity.Cinema;
import com.movie.ordering.system.order.service.domain.ports.output.repository.CinemaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CinemaRepositoryImpl implements CinemaRepository {

    private final CinemaJpaRepository cinemaJpaRepository;
    private final CinemaDataAccessMapper cinemaDataAccessMapper;

    public CinemaRepositoryImpl(
                                CinemaJpaRepository cinemaJpaRepository, CinemaDataAccessMapper cinemaDataAccessMapper) {
        this.cinemaJpaRepository = cinemaJpaRepository;

        this.cinemaDataAccessMapper = cinemaDataAccessMapper;
    }

    @Override
    public Optional<Cinema> findCinemaInformation(Cinema cinema) {
        List<UUID> cinemaMovies =
                cinemaDataAccessMapper.cinemaToCinemaMovies(cinema);
        Optional<List<CinemaEntity>> cinemaEntities =
                cinemaJpaRepository.findByCinemaIdAndMovieIdIn(cinema.getId().getValue(),cinemaMovies);
        return cinemaEntities.map(cinemaDataAccessMapper::cinemaEntityToCinema);
    }
}
