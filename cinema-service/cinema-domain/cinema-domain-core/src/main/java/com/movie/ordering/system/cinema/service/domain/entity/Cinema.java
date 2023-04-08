package com.movie.ordering.system.cinema.service.domain.entity;

import com.movie.ordering.system.cinema.service.domain.valueobject.OrderApprovalId;
import com.movie.ordering.system.domain.entity.AggregateRoot;
import com.movie.ordering.system.domain.valueobject.CinemaId;
import com.movie.ordering.system.domain.valueobject.Money;
import com.movie.ordering.system.domain.valueobject.OrderApprovalStatus;
import com.movie.ordering.system.domain.valueobject.OrderStatus;

import java.util.List;
import java.util.UUID;

public class Cinema extends AggregateRoot<CinemaId> {
    private final OrderDetail orderDetail;
    private OrderApproval orderApproval;
    private boolean active;

    private Cinema(Builder builder) {
        setId(builder.cinemaId);
        orderApproval = builder.orderApproval;
        active = builder.active;
        orderDetail = builder.orderDetail;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void validateOrder(List<String> failureMessages) {
        if (orderDetail.getOrderStatus() != OrderStatus.PAID) {
            failureMessages.add("Payment is not completed for order: " + orderDetail.getId());
        }
        Money totalAmount = orderDetail.getMovies().stream().map(movie -> {
            if (!movie.isAvailable()) {
                failureMessages.add("Movie with id: " + movie.getId().getValue()
                        + " is not available");
            }
            return movie.getPrice().multiply(movie.getQuantity());
        }).reduce(Money.ZERO, Money::add);

        if (!totalAmount.equals(orderDetail.getTotalAmount())) {
            failureMessages.add("Price total is not correct for order: " + orderDetail.getId());
        }
    }

    public void constructOrderApproval(OrderApprovalStatus orderApprovalStatus) {
        this.orderApproval = OrderApproval.builder()
                .orderApprovalId(new OrderApprovalId(UUID.randomUUID()))
                .cinemaId(this.getId())
                .orderId(this.getOrderDetail().getId())
                .approvalStatus(orderApprovalStatus)
                .build();
    }

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public static final class Builder {
        private CinemaId cinemaId;
        private OrderApproval orderApproval;
        private boolean active;
        private OrderDetail orderDetail;

        private Builder() {
        }

        public Builder cinemaId(CinemaId val) {
            cinemaId = val;
            return this;
        }

        public Builder orderApproval(OrderApproval val) {
            orderApproval = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Builder orderDetail(OrderDetail val) {
            orderDetail = val;
            return this;
        }

        public Cinema build() {
            return new Cinema(this);
        }
    }
}
