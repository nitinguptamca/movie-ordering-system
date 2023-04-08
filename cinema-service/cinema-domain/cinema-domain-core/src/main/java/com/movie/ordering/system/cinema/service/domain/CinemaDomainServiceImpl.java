package com.movie.ordering.system.cinema.service.domain;

import com.movie.ordering.system.cinema.service.domain.entity.Cinema;
import com.movie.ordering.system.cinema.service.domain.event.OrderApprovalEvent;
import com.movie.ordering.system.cinema.service.domain.event.OrderApprovedEvent;
import com.movie.ordering.system.cinema.service.domain.event.OrderRejectedEvent;
import com.movie.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.movie.ordering.system.domain.valueobject.OrderApprovalStatus;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.movie.ordering.system.domain.DomainConstants.UTC;

@Slf4j
public class CinemaDomainServiceImpl implements CinemaDomainService {

    @Override
    public OrderApprovalEvent validateOrder(Cinema cinema,
                                            List<String> failureMessages,
                                            DomainEventPublisher<OrderApprovedEvent>
                                                    orderApprovedEventDomainEventPublisher,
                                            DomainEventPublisher<OrderRejectedEvent>
                                                    orderRejectedEventDomainEventPublisher) {
        cinema.validateOrder(failureMessages);
        log.info("Validating order with id: {}", cinema.getOrderDetail().getId().getValue());

        if (failureMessages.isEmpty()) {
            log.info("Order is approved for order id: {}", cinema.getOrderDetail().getId().getValue());
            cinema.constructOrderApproval(OrderApprovalStatus.APPROVED);
            return new OrderApprovedEvent(cinema.getOrderApproval(),
                    cinema.getId(),
                    failureMessages,
                    ZonedDateTime.now(ZoneId.of(UTC)),
                    orderApprovedEventDomainEventPublisher);
        } else {
            log.info("Order is rejected for order id: {}", cinema.getOrderDetail().getId().getValue());
            cinema.constructOrderApproval(OrderApprovalStatus.REJECTED);
            return new OrderRejectedEvent(cinema.getOrderApproval(),
                    cinema.getId(),
                    failureMessages,
                    ZonedDateTime.now(ZoneId.of(UTC)),
                    orderRejectedEventDomainEventPublisher);
        }
    }
}
