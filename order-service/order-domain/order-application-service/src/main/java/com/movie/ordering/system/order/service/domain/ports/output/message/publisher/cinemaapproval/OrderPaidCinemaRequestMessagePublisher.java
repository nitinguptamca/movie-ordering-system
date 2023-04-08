package com.movie.ordering.system.order.service.domain.ports.output.message.publisher.cinemaapproval;

import com.movie.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.movie.ordering.system.order.service.domain.event.OrderPaidEvent;

public interface OrderPaidCinemaRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
