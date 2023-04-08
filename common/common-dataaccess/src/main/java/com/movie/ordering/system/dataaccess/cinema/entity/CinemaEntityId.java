package com.movie.ordering.system.dataaccess.cinema.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CinemaEntityId implements Serializable {

    private UUID cinemaId;
    private UUID movieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaEntityId that = (CinemaEntityId) o;
        return Objects.equals(cinemaId, that.cinemaId) && Objects.equals(movieId, that.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cinemaId, movieId);
    }
}
