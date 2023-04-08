package com.movie.ordering.system.cinema.service.domain.event;

import com.movie.ordering.system.cinema.service.domain.entity.OrderApproval;
import com.movie.ordering.system.domain.event.DomainEvent;
import com.movie.ordering.system.domain.valueobject.CinemaId;

import java.time.ZonedDateTime;
import java.util.List;

public abstract class OrderApprovalEvent implements DomainEvent<OrderApproval> {
    private final OrderApproval orderApproval;
    private final CinemaId cinemaId;
    private final List<String> failureMessages;
    private final ZonedDateTime createdAt;

    public OrderApprovalEvent(OrderApproval orderApproval,
                              CinemaId cinemaId,
                              List<String> failureMessages,
                              ZonedDateTime createdAt) {
        this.orderApproval = orderApproval;
        this.cinemaId = cinemaId;
        this.failureMessages = failureMessages;
        this.createdAt = createdAt;
    }

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public CinemaId getCinemaId() {
        return cinemaId;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
