package com.movie.ordering.system.cinema.service.domain.ports.output.message.publisher;

import com.movie.ordering.system.cinema.service.domain.event.OrderRejectedEvent;
import com.movie.ordering.system.domain.event.publisher.DomainEventPublisher;

public interface OrderRejectedMessagePublisher extends DomainEventPublisher<OrderRejectedEvent> {
}
