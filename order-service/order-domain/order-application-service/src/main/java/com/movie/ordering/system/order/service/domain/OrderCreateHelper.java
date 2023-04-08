package com.movie.ordering.system.order.service.domain;

import com.movie.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.movie.ordering.system.order.service.domain.entity.Cinema;
import com.movie.ordering.system.order.service.domain.entity.Customer;
import com.movie.ordering.system.order.service.domain.entity.Order;
import com.movie.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.movie.ordering.system.order.service.domain.exception.OrderDomainException;
import com.movie.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.movie.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.movie.ordering.system.order.service.domain.ports.output.repository.CinemaRepository;
import com.movie.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import com.movie.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderCreateHelper {

    private final OrderDomainService orderDomainService;

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final CinemaRepository cinemaRepository;

    private final OrderDataMapper orderDataMapper;

    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedEventDomainEventPublisher;

    public OrderCreateHelper(OrderDomainService orderDomainService,
                             OrderRepository orderRepository,
                             CustomerRepository customerRepository,
                             CinemaRepository cinemaRepository,
                             OrderDataMapper orderDataMapper,
                             OrderCreatedPaymentRequestMessagePublisher orderCreatedEventDomainEventPublisher) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.cinemaRepository = cinemaRepository;
        this.orderDataMapper = orderDataMapper;
        this.orderCreatedEventDomainEventPublisher = orderCreatedEventDomainEventPublisher;
    }

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Cinema cinema = checkCinema(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, cinema,
                orderCreatedEventDomainEventPublisher);
        saveOrder(order);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderCreatedEvent;
    }

    private Cinema checkCinema(CreateOrderCommand createOrderCommand) {
        Cinema cinema = orderDataMapper.createOrderCommandToCinema(createOrderCommand);
        Optional<Cinema> optionalCinema = cinemaRepository.findCinemaInformation(cinema);
        if (optionalCinema.isEmpty()) {
            log.warn("Could not find cinema with cinema id: {}", createOrderCommand.getCinemaId());
            throw new OrderDomainException("Could not find cinema with cinema id: " +
                    createOrderCommand.getCinemaId());
        }
        return optionalCinema.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);
        if (customer.isEmpty()) {
            log.warn("Could not find customer with customer id: {}", customerId);
            throw new OrderDomainException("Could not find customer with customer id: " + customer);
        }
    }

    private Order saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        if (orderResult == null) {
            log.error("Could not save order!");
            throw new OrderDomainException("Could not save order!");
        }
        log.info("Order is saved with id: {}", orderResult.getId().getValue());
        return orderResult;
    }
}
