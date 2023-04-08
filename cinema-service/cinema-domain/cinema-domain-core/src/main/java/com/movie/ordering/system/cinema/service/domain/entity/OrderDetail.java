package com.movie.ordering.system.cinema.service.domain.entity;

import com.movie.ordering.system.domain.entity.BaseEntity;
import com.movie.ordering.system.domain.valueobject.Money;
import com.movie.ordering.system.domain.valueobject.OrderId;
import com.movie.ordering.system.domain.valueobject.OrderStatus;

import java.util.List;

public class OrderDetail extends BaseEntity<OrderId> {
    private final List<Movie> movies;
    private OrderStatus orderStatus;
    private Money totalAmount;

    private OrderDetail(Builder builder) {
        setId(builder.orderId);
        orderStatus = builder.orderStatus;
        totalAmount = builder.totalAmount;
        movies = builder.movies;
    }

    public static Builder builder() {
        return new Builder();
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public static final class Builder {
        private OrderId orderId;
        private OrderStatus orderStatus;
        private Money totalAmount;
        private List<Movie> movies;

        private Builder() {
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder totalAmount(Money val) {
            totalAmount = val;
            return this;
        }

        public Builder movies(List<Movie> val) {
            movies = val;
            return this;
        }

        public OrderDetail build() {
            return new OrderDetail(this);
        }
    }
}
