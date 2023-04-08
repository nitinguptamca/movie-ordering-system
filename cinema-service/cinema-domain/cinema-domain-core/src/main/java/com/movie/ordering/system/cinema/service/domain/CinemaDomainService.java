package com.movie.ordering.system.cinema.service.domain;

import com.movie.ordering.system.cinema.service.domain.entity.Cinema;
import com.movie.ordering.system.cinema.service.domain.event.OrderApprovalEvent;
import com.movie.ordering.system.cinema.service.domain.event.OrderApprovedEvent;
import com.movie.ordering.system.cinema.service.domain.event.OrderRejectedEvent;
import com.movie.ordering.system.domain.event.publisher.DomainEventPublisher;

import java.util.List;

public interface CinemaDomainService {

    OrderApprovalEvent validateOrder(Cinema cinema,
                                     List<String> failureMessages,
                                     DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher,
                                     DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher);
}
