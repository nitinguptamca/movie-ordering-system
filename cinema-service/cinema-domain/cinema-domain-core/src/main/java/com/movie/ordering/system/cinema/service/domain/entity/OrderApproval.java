package com.movie.ordering.system.cinema.service.domain.entity;

import com.movie.ordering.system.cinema.service.domain.valueobject.OrderApprovalId;
import com.movie.ordering.system.domain.entity.BaseEntity;
import com.movie.ordering.system.domain.valueobject.CinemaId;
import com.movie.ordering.system.domain.valueobject.OrderApprovalStatus;
import com.movie.ordering.system.domain.valueobject.OrderId;

public class OrderApproval extends BaseEntity<OrderApprovalId> {
    private final CinemaId cinemaId;
    private final OrderId orderId;
    private final OrderApprovalStatus approvalStatus;

    private OrderApproval(Builder builder) {
        setId(builder.orderApprovalId);
        cinemaId = builder.cinemaId;
        orderId = builder.orderId;
        approvalStatus = builder.approvalStatus;
    }

    public static Builder builder() {
        return new Builder();
    }


    public CinemaId getCinemaId() {
        return cinemaId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public OrderApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public static final class Builder {
        private OrderApprovalId orderApprovalId;
        private CinemaId cinemaId;
        private OrderId orderId;
        private OrderApprovalStatus approvalStatus;

        private Builder() {
        }

        public Builder orderApprovalId(OrderApprovalId val) {
            orderApprovalId = val;
            return this;
        }

        public Builder cinemaId(CinemaId val) {
            cinemaId = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder approvalStatus(OrderApprovalStatus val) {
            approvalStatus = val;
            return this;
        }

        public OrderApproval build() {
            return new OrderApproval(this);
        }
    }
}
