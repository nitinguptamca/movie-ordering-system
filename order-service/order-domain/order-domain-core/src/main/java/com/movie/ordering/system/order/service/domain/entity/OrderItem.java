package com.movie.ordering.system.order.service.domain.entity;

import com.movie.ordering.system.domain.entity.BaseEntity;
import com.movie.ordering.system.domain.valueobject.Money;
import com.movie.ordering.system.domain.valueobject.OrderId;
import com.movie.ordering.system.order.service.domain.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {
    private final Movie movie;
    private final int quantity;
    private final Money price;
    private final Money subTotal;
    private OrderId orderId;

    private OrderItem(Builder builder) {
        super.setId(builder.orderItemId);
        movie = builder.movie;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
    }

    public static Builder builder() {
        return new Builder();
    }

    void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }

    boolean isPriceValid() {
        return price.isGreaterThanZero() &&
                price.equals(movie.getPrice()) &&
                price.multiply(quantity).equals(subTotal);
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public static final class Builder {
        private OrderItemId orderItemId;
        private Movie movie;
        private int quantity;
        private Money price;
        private Money subTotal;

        private Builder() {
        }

        public Builder orderItemId(OrderItemId val) {
            orderItemId = val;
            return this;
        }

        public Builder movie(Movie val) {
            movie = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
