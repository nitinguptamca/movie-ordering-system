package com.movie.ordering.system.order.service.domain;

import com.movie.ordering.system.domain.event.EmptyEvent;
import com.movie.ordering.system.order.service.domain.dto.message.CinemaApprovalResponse;
import com.movie.ordering.system.order.service.domain.entity.Order;
import com.movie.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.movie.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.movie.ordering.system.saga.SagaStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderApprovalSaga implements SagaStep<CinemaApprovalResponse, EmptyEvent, OrderCancelledEvent> {

    private final OrderDomainService orderDomainService;
    private final OrderSagaHelper orderSagaHelper;
    private final OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher;

    public OrderApprovalSaga(OrderDomainService orderDomainService,
                             OrderSagaHelper orderSagaHelper,
                             OrderCancelledPaymentRequestMessagePublisher
                                     orderCancelledPaymentRequestMessagePublisher) {
        this.orderDomainService = orderDomainService;
        this.orderSagaHelper = orderSagaHelper;
        this.orderCancelledPaymentRequestMessagePublisher = orderCancelledPaymentRequestMessagePublisher;
    }

    @Override
    @Transactional
    public EmptyEvent process(CinemaApprovalResponse cinemaApprovalResponse) {
        log.info("Approving order with id: {}", cinemaApprovalResponse.getOrderId());
        Order order = orderSagaHelper.findOrder(cinemaApprovalResponse.getOrderId());
        orderDomainService.approveOrder(order);
        orderSagaHelper.saveOrder(order);
        log.info("Order with id: {} is approved", order.getId().getValue());
        return EmptyEvent.INSTANCE;
    }

    @Override
    @Transactional
    public OrderCancelledEvent rollback(CinemaApprovalResponse cinemaApprovalResponse) {
        log.info("Cancelling order with id: {}", cinemaApprovalResponse.getOrderId());
        Order order = orderSagaHelper.findOrder(cinemaApprovalResponse.getOrderId());
        OrderCancelledEvent domainEvent = orderDomainService.cancelOrderPayment(order,
                cinemaApprovalResponse.getFailureMessages(),
                orderCancelledPaymentRequestMessagePublisher);
        orderSagaHelper.saveOrder(order);
        log.info("Order with id: {} is cancelling", order.getId().getValue());
        return domainEvent;
    }
}
