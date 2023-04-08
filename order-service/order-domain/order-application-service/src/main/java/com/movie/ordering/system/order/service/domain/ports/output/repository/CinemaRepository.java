package com.movie.ordering.system.order.service.domain.ports.output.repository;

import com.movie.ordering.system.order.service.domain.entity.Cinema;

import java.util.Optional;

public interface CinemaRepository {

    Optional<Cinema> findCinemaInformation(Cinema cinema);
}
