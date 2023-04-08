package com.movie.ordering.system.cinema.service.domain.entity;

import com.movie.ordering.system.domain.entity.BaseEntity;
import com.movie.ordering.system.domain.valueobject.Money;
import com.movie.ordering.system.domain.valueobject.MovieId;


public class Movie extends BaseEntity<MovieId> {
    private final int quantity;
    private String name;
    private Money price;
    private boolean available;

    private Movie(Builder builder) {
        setId(builder.movieId);
        name = builder.name;
        price = builder.price;
        quantity = builder.quantity;
        available = builder.available;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void updateWithConfirmedNamePriceAndAvailability(String name, Money price, boolean available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isAvailable() {
        return available;
    }

    public static final class Builder {
        private MovieId movieId;
        private String name;
        private Money price;
        private int quantity;
        private boolean available;

        private Builder() {
        }

        public Builder movieId(MovieId val) {
            movieId = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder available(boolean val) {
            available = val;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
