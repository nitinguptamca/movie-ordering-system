package com.movie.ordering.system.payment.service.domain.ports.output.message.publisher;

import com.movie.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.movie.ordering.system.payment.service.domain.event.PaymentCancelledEvent;

public interface PaymentCancelledMessagePublisher extends DomainEventPublisher<PaymentCancelledEvent> {
}
