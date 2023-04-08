package com.movie.ordering.system.order.service.messaging.mapper;

import com.movie.ordering.system.kafka.order.avro.model.*;
import com.movie.ordering.system.order.service.domain.dto.message.CinemaApprovalResponse;
import com.movie.ordering.system.order.service.domain.dto.message.PaymentResponse;
import com.movie.ordering.system.order.service.domain.entity.Order;
import com.movie.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.movie.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.movie.ordering.system.order.service.domain.event.OrderPaidEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderMessagingDataMapper {

    public PaymentRequestAvroModel orderCreatedEventToPaymentRequestAvroModel(OrderCreatedEvent orderCreatedEvent) {
        Order order = orderCreatedEvent.getOrder();
        return PaymentRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setCustomerId(order.getCustomerId().getValue().toString())
                .setOrderId(order.getId().getValue().toString())
                .setPrice(order.getPrice().getAmount())
                .setCreatedAt(orderCreatedEvent.getCreatedAt().toInstant())
                .setPaymentOrderStatus(PaymentOrderStatus.PENDING)
                .build();
    }

    public PaymentRequestAvroModel orderCancelledEventToPaymentRequestAvroModel(OrderCancelledEvent orderCancelledEvent) {
        Order order = orderCancelledEvent.getOrder();
        return PaymentRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setCustomerId(order.getCustomerId().getValue().toString())
                .setOrderId(order.getId().getValue().toString())
                .setPrice(order.getPrice().getAmount())
                .setCreatedAt(orderCancelledEvent.getCreatedAt().toInstant())
                .setPaymentOrderStatus(PaymentOrderStatus.CANCELLED)
                .build();
    }

    public CinemaApprovalRequestAvroModel
    orderPaidEventToCinemaApprovalRequestAvroModel(OrderPaidEvent orderPaidEvent) {
        Order order = orderPaidEvent.getOrder();
        return CinemaApprovalRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setOrderId(order.getId().getValue().toString())
                .setCinemaId(order.getCinemaId().getValue().toString())
                .setOrderId(order.getId().getValue().toString())
                .setCinemaOrderStatus(com.movie.ordering.system.kafka.order.avro.model.CinemaOrderStatus
                        .valueOf(order.getOrderStatus().name()))
                .setMovies(order.getItems().stream().map(orderItem ->
                        com.movie.ordering.system.kafka.order.avro.model.Movie.newBuilder()
                                .setId(orderItem.getMovie().getId().getValue().toString())
                                .setQuantity(orderItem.getQuantity())
                                .build()).collect(Collectors.toList()))
                .setPrice(order.getPrice().getAmount())
                .setCreatedAt(orderPaidEvent.getCreatedAt().toInstant())
                .setCinemaOrderStatus(CinemaOrderStatus.PAID)
                .build();
    }

    public PaymentResponse paymentResponseAvroModelToPaymentResponse(PaymentResponseAvroModel
                                                                             paymentResponseAvroModel) {
        return PaymentResponse.builder()
                .id(paymentResponseAvroModel.getId())
                .sagaId(paymentResponseAvroModel.getSagaId())
                .paymentId(paymentResponseAvroModel.getPaymentId())
                .customerId(paymentResponseAvroModel.getCustomerId())
                .orderId(paymentResponseAvroModel.getOrderId())
                .price(paymentResponseAvroModel.getPrice())
                .createdAt(paymentResponseAvroModel.getCreatedAt())
                .paymentStatus(com.movie.ordering.system.domain.valueobject.PaymentStatus.valueOf(
                        paymentResponseAvroModel.getPaymentStatus().name()))
                .failureMessages(paymentResponseAvroModel.getFailureMessages())
                .build();
    }

    public CinemaApprovalResponse
    approvalResponseAvroModelToApprovalResponse(CinemaApprovalResponseAvroModel
                                                        cinemaApprovalResponseAvroModel) {
        return CinemaApprovalResponse.builder()
                .id(cinemaApprovalResponseAvroModel.getId())
                .sagaId(cinemaApprovalResponseAvroModel.getSagaId())
                .cinemaId(cinemaApprovalResponseAvroModel.getCinemaId())
                .orderId(cinemaApprovalResponseAvroModel.getOrderId())
                .createdAt(cinemaApprovalResponseAvroModel.getCreatedAt())
                .orderApprovalStatus(com.movie.ordering.system.domain.valueobject.OrderApprovalStatus.valueOf(
                        cinemaApprovalResponseAvroModel.getOrderApprovalStatus().name()))
                .failureMessages(cinemaApprovalResponseAvroModel.getFailureMessages())
                .build();
    }
}
