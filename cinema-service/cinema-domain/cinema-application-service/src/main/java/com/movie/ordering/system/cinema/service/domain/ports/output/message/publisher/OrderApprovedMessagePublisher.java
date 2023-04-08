package com.movie.ordering.system.cinema.service.domain.ports.output.message.publisher;

import com.movie.ordering.system.cinema.service.domain.event.OrderApprovedEvent;
import com.movie.ordering.system.domain.event.publisher.DomainEventPublisher;

public interface OrderApprovedMessagePublisher extends DomainEventPublisher<OrderApprovedEvent> {
}
