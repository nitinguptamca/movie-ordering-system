package com.movie.ordering.system.cinema.service.domain.event;

import com.movie.ordering.system.cinema.service.domain.entity.OrderApproval;
import com.movie.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.movie.ordering.system.domain.valueobject.CinemaId;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderApprovedEvent extends OrderApprovalEvent {

    private final DomainEventPublisher<OrderApprovedEvent>
            orderApprovedEventDomainEventPublisher;

    public OrderApprovedEvent(OrderApproval orderApproval,
                              CinemaId cinemaId,
                              List<String> failureMessages,
                              ZonedDateTime createdAt,
                              DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher) {
        super(orderApproval, cinemaId, failureMessages, createdAt);
        this.orderApprovedEventDomainEventPublisher = orderApprovedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        orderApprovedEventDomainEventPublisher.publish(this);
    }
}
