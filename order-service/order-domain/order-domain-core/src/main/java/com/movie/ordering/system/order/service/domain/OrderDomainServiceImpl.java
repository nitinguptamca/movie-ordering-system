package com.movie.ordering.system.order.service.domain;

import com.movie.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.movie.ordering.system.order.service.domain.entity.Cinema;
import com.movie.ordering.system.order.service.domain.entity.Movie;
import com.movie.ordering.system.order.service.domain.entity.Order;
import com.movie.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.movie.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.movie.ordering.system.order.service.domain.event.OrderPaidEvent;
import com.movie.ordering.system.order.service.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.movie.ordering.system.domain.DomainConstants.UTC;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Cinema cinema,
                                                      DomainEventPublisher<OrderCreatedEvent>
                                                              orderCreatedEventDomainEventPublisher) {
        validateCinema(cinema);
        setOrderMovieInformation(order, cinema);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id: {} is initiated", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)), orderCreatedEventDomainEventPublisher);
    }

    @Override
    public OrderPaidEvent payOrder(Order order,
                                   DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher) {
        order.pay();
        log.info("Order with id: {} is paid", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)), orderPaidEventDomainEventPublisher);
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id: {} is approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages,
                                                  DomainEventPublisher<OrderCancelledEvent>
                                                          orderCancelledEventDomainEventPublisher) {
        order.initCancel(failureMessages);
        log.info("Order payment is cancelling for order id: {}", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)),
                orderCancelledEventDomainEventPublisher);
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id: {} is cancelled", order.getId().getValue());
    }

    private void validateCinema(Cinema cinema) {
        if (!cinema.isActive()) {
            throw new OrderDomainException("Cinema with id " + cinema.getId().getValue() +
                    " is currently not active!");
        }
    }

    private void setOrderMovieInformation(Order order, Cinema cinema) {
        order.getItems().forEach(orderItem -> cinema.getMovies().forEach(cinemaMovies -> {
            Movie currentMovie = orderItem.getMovie();
            if (currentMovie.equals(cinemaMovies)) {
                currentMovie.updateWithConfirmedNameAndPrice(cinemaMovies.getName(),
                        cinemaMovies.getPrice());
            }
        }));
    }
}
