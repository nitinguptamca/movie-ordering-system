package com.movie.ordering.system.cinema.service.domain.ports.output.repository;

import com.movie.ordering.system.cinema.service.domain.entity.Cinema;

import java.util.Optional;

public interface CinemaRepository {
    Optional<Cinema> findCinemaInformation(Cinema cinema );
}
