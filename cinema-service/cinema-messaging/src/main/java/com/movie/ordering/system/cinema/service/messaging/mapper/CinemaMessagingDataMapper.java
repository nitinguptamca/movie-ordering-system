package com.movie.ordering.system.cinema.service.messaging.mapper;

import com.movie.ordering.system.cinema.service.domain.dto.CinemaApprovalRequest;
import com.movie.ordering.system.cinema.service.domain.entity.Movie;
import com.movie.ordering.system.cinema.service.domain.event.OrderApprovedEvent;
import com.movie.ordering.system.cinema.service.domain.event.OrderRejectedEvent;
import com.movie.ordering.system.domain.valueobject.CinemaOrderStatus;
import com.movie.ordering.system.domain.valueobject.MovieId;
import com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel;
import com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalResponseAvroModel;
import com.movie.ordering.system.kafka.order.avro.model.OrderApprovalStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CinemaMessagingDataMapper {
    public CinemaApprovalResponseAvroModel
    orderApprovedEventToCinemaApprovalResponseAvroModel(OrderApprovedEvent orderApprovedEvent) {
        return CinemaApprovalResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setOrderId(orderApprovedEvent.getOrderApproval().getOrderId().getValue().toString())
                .setCinemaId(orderApprovedEvent.getCinemaId().getValue().toString())
                .setCreatedAt(orderApprovedEvent.getCreatedAt().toInstant())
                .setOrderApprovalStatus(OrderApprovalStatus.valueOf(orderApprovedEvent.
                        getOrderApproval().getApprovalStatus().name()))
                .setFailureMessages(orderApprovedEvent.getFailureMessages())
                .build();
    }

    public CinemaApprovalResponseAvroModel
    orderRejectedEventToCinemaApprovalResponseAvroModel(OrderRejectedEvent orderRejectedEvent) {
        return CinemaApprovalResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setOrderId(orderRejectedEvent.getOrderApproval().getOrderId().getValue().toString())
                .setCinemaId(orderRejectedEvent.getCinemaId().getValue().toString())
                .setCreatedAt(orderRejectedEvent.getCreatedAt().toInstant())
                .setOrderApprovalStatus(OrderApprovalStatus.valueOf(orderRejectedEvent.
                        getOrderApproval().getApprovalStatus().name()))
                .setFailureMessages(orderRejectedEvent.getFailureMessages())
                .build();
    }

    public CinemaApprovalRequest
    cinemaApprovalRequestAvroModelToCinemaApproval(CinemaApprovalRequestAvroModel
                                                           cinemaApprovalRequestAvroModel) {
        return CinemaApprovalRequest.builder()
                .id(cinemaApprovalRequestAvroModel.getId())
                .sagaId(cinemaApprovalRequestAvroModel.getSagaId())
                .cinemaId(cinemaApprovalRequestAvroModel.getCinemaId())
                .orderId(cinemaApprovalRequestAvroModel.getOrderId())
                .cinemaOrderStatus(
                        CinemaOrderStatus.valueOf(cinemaApprovalRequestAvroModel
                        .getCinemaOrderStatus().name()))
                .movies(cinemaApprovalRequestAvroModel.getMovies()
                        .stream().map(avroModel ->
                                Movie.builder()
                                        .movieId(new MovieId(UUID.fromString(avroModel.getId())))
                                        .quantity(avroModel.getQuantity())
                                        .build())
                        .collect(Collectors.toList()))
                .price(cinemaApprovalRequestAvroModel.getPrice())
                .createdAt(cinemaApprovalRequestAvroModel.getCreatedAt())
                .build();
    }
}
