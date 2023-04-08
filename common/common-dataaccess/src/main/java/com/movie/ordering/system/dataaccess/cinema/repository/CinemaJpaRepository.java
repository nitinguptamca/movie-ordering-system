package com.movie.ordering.system.dataaccess.cinema.repository;


import com.movie.ordering.system.dataaccess.cinema.entity.CinemaEntity;
import com.movie.ordering.system.dataaccess.cinema.entity.CinemaEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CinemaJpaRepository extends JpaRepository<CinemaEntity, CinemaEntityId> {

    Optional<List<CinemaEntity>> findByCinemaIdAndMovieIdIn(UUID cinemaId, List<UUID> movieIds);
}
