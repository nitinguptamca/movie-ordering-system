package com.movie.ordering.system.dataaccess.cinema.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CinemaEntityId.class)
@Table(name = "order_cinema_m_view", schema = "cinema")
@Entity
public class CinemaEntity {

    @Id
    private UUID cinemaId;
    @Id
    private UUID movieId;
    private String cinemaName;
    private Boolean cinemaActive;
    private String movieName;
    private BigDecimal moviePrice;
    private Boolean movieAvailable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaEntity that = (CinemaEntity) o;
        return Objects.equals(cinemaId, that.cinemaId) && Objects.equals(movieId, that.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cinemaId, movieId);
    }
}
