package com.movie.ordering.system.order.service.domain.entity;

import com.movie.ordering.system.domain.entity.AggregateRoot;
import com.movie.ordering.system.domain.valueobject.CinemaId;

import java.util.List;

public class Cinema extends AggregateRoot<CinemaId> {
    private final List<Movie> movies;
    private boolean active;

    private Cinema(Builder builder) {
        super.setId(builder.cinemaId);
        movies = builder.movies;
        active = builder.active;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public boolean isActive() {
        return active;
    }

    public static final class Builder {
        private CinemaId cinemaId;
        private List<Movie> movies;
        private boolean active;

        private Builder() {
        }

        public Builder cinemaId(CinemaId val) {
            cinemaId = val;
            return this;
        }

        public Builder movies(List<Movie> val) {
            movies = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Cinema build() {
            return new Cinema(this);
        }
    }
}
