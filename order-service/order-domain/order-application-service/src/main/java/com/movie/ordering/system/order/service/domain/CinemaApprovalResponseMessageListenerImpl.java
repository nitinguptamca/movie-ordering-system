package com.movie.ordering.system.order.service.domain;

import com.movie.ordering.system.order.service.domain.dto.message.CinemaApprovalResponse;
import com.movie.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.movie.ordering.system.order.service.domain.ports.input.message.listener.cinemaapproval.CinemaApprovalResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.movie.ordering.system.order.service.domain.entity.Order.FAILURE_MESSAGE_DELIMITER;

@Slf4j
@Validated
@Service
public class CinemaApprovalResponseMessageListenerImpl implements CinemaApprovalResponseMessageListener {

    private final OrderApprovalSaga orderApprovalSaga;

    public CinemaApprovalResponseMessageListenerImpl(OrderApprovalSaga orderApprovalSaga) {
        this.orderApprovalSaga = orderApprovalSaga;
    }

    @Override
    public void orderApproved(CinemaApprovalResponse cinemaApprovalResponse) {
        orderApprovalSaga.process(cinemaApprovalResponse);
        log.info("Order is approved for order id: {}", cinemaApprovalResponse.getOrderId());
    }

    @Override
    public void orderRejected(CinemaApprovalResponse cinemaApprovalResponse) {
        OrderCancelledEvent domainEvent = orderApprovalSaga.rollback(cinemaApprovalResponse);
        log.info("Publishing order cancelled event for order id: {} with failure messages: {}",
                cinemaApprovalResponse.getOrderId(),
                String.join(FAILURE_MESSAGE_DELIMITER, cinemaApprovalResponse.getFailureMessages()));
        domainEvent.fire();
    }
}
