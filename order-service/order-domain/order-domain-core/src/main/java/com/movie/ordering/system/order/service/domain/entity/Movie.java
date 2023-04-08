package com.movie.ordering.system.order.service.domain.entity;

import com.movie.ordering.system.domain.entity.BaseEntity;
import com.movie.ordering.system.domain.valueobject.Money;
import com.movie.ordering.system.domain.valueobject.MovieId;

public class Movie extends BaseEntity<MovieId> {
    private String name;
    private Money price;

    public Movie(MovieId movieId, String name, Money price) {
        super.setId(movieId);
        this.name = name;
        this.price = price;
    }

    public Movie(MovieId movieId) {
        super.setId(movieId);
    }

    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}
