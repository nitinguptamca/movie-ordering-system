package com.movie.ordering.system.payment.service.domain;

import com.movie.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.movie.ordering.system.payment.service.domain.entity.CreditEntry;
import com.movie.ordering.system.payment.service.domain.entity.CreditHistory;
import com.movie.ordering.system.payment.service.domain.entity.Payment;
import com.movie.ordering.system.payment.service.domain.event.PaymentCancelledEvent;
import com.movie.ordering.system.payment.service.domain.event.PaymentCompletedEvent;
import com.movie.ordering.system.payment.service.domain.event.PaymentEvent;
import com.movie.ordering.system.payment.service.domain.event.PaymentFailedEvent;

import java.util.List;

public interface PaymentDomainService {

    PaymentEvent validateAndInitiatePayment(Payment payment,
                                            CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories,
                                            List<String> failureMessages,
                                            DomainEventPublisher<PaymentCompletedEvent>
                                                    paymentCompletedEventDomainEventPublisher, DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);

    PaymentEvent validateAndCancelPayment(Payment payment,
                                          CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories,
                                          List<String> failureMessages, DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher, DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);
}
