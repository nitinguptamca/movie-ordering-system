package com.movie.ordering.system.order.service.domain.mapper;

import com.movie.ordering.system.domain.valueobject.CinemaId;
import com.movie.ordering.system.domain.valueobject.CustomerId;
import com.movie.ordering.system.domain.valueobject.Money;
import com.movie.ordering.system.domain.valueobject.MovieId;
import com.movie.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.movie.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.movie.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.movie.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.movie.ordering.system.order.service.domain.entity.Cinema;
import com.movie.ordering.system.order.service.domain.entity.Movie;
import com.movie.ordering.system.order.service.domain.entity.Order;
import com.movie.ordering.system.order.service.domain.entity.OrderItem;
import com.movie.ordering.system.order.service.domain.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {

    public Cinema createOrderCommandToCinema(CreateOrderCommand createOrderCommand) {
        return Cinema.builder()
                .cinemaId(new CinemaId(createOrderCommand.getCinemaId()))
                .movies(createOrderCommand.getItems().stream().map(orderItem ->
                                new Movie(new MovieId(orderItem.getMovieId())))
                        .collect(Collectors.toList()))
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .cinemaId(new CinemaId(createOrderCommand.getCinemaId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .message(message)
                .build();
    }

    public TrackOrderResponse orderToTrackOrderResponse(Order order) {
        return TrackOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages())
                .build();
    }

    private List<OrderItem> orderItemsToOrderItemEntities(
            List<com.movie.ordering.system.order.service.domain.dto.create.OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem ->
                        OrderItem.builder()
                                .movie(new Movie(new MovieId(orderItem.getMovieId())))
                                .price(new Money(orderItem.getPrice()))
                                .quantity(orderItem.getQuantity())
                                .subTotal(new Money(orderItem.getSubTotal()))
                                .build()).collect(Collectors.toList());
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
        return new StreetAddress(
                UUID.randomUUID(),
                orderAddress.getStreet(),
                orderAddress.getPostalCode(),
                orderAddress.getCity()
        );
    }
}
