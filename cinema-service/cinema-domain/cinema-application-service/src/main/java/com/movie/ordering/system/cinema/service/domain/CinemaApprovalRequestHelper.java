package com.movie.ordering.system.cinema.service.domain;

import com.movie.ordering.system.cinema.service.domain.dto.CinemaApprovalRequest;
import com.movie.ordering.system.cinema.service.domain.entity.Cinema;
import com.movie.ordering.system.cinema.service.domain.event.OrderApprovalEvent;
import com.movie.ordering.system.cinema.service.domain.exception.CinemaNotFoundException;
import com.movie.ordering.system.cinema.service.domain.mapper.CinemaDataMapper;
import com.movie.ordering.system.cinema.service.domain.ports.output.message.publisher.OrderApprovedMessagePublisher;
import com.movie.ordering.system.cinema.service.domain.ports.output.message.publisher.OrderRejectedMessagePublisher;
import com.movie.ordering.system.cinema.service.domain.ports.output.repository.CinemaRepository;
import com.movie.ordering.system.cinema.service.domain.ports.output.repository.OrderApprovalRepository;
import com.movie.ordering.system.domain.valueobject.OrderId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class CinemaApprovalRequestHelper {

    private final CinemaDomainService cinemaDomainService;
    private final CinemaDataMapper cinemaDataMapper;
    private final CinemaRepository cinemaRepository;
    private final OrderApprovalRepository orderApprovalRepository;
    private final OrderApprovedMessagePublisher orderApprovedMessagePublisher;
    private final OrderRejectedMessagePublisher orderRejectedMessagePublisher;

    public CinemaApprovalRequestHelper(CinemaDomainService cinemaDomainService,
                                       CinemaDataMapper cinemaDataMapper,
                                       CinemaRepository cinemaRepository,
                                       OrderApprovalRepository orderApprovalRepository,
                                       OrderApprovedMessagePublisher orderApprovedMessagePublisher,
                                       OrderRejectedMessagePublisher orderRejectedMessagePublisher) {
        this.cinemaDomainService = cinemaDomainService;
        this.cinemaDataMapper = cinemaDataMapper;
        this.cinemaRepository = cinemaRepository;
        this.orderApprovalRepository = orderApprovalRepository;
        this.orderApprovedMessagePublisher = orderApprovedMessagePublisher;
        this.orderRejectedMessagePublisher = orderRejectedMessagePublisher;
    }

    @Transactional
    public OrderApprovalEvent persistOrderApproval(CinemaApprovalRequest cinemaApprovalRequest) {
        log.info("Processing cinema approval for order id: {}", cinemaApprovalRequest.getOrderId());
        List<String> failureMessages = new ArrayList<>();
        Cinema cinema = findCinema(cinemaApprovalRequest);
        OrderApprovalEvent orderApprovalEvent =
                cinemaDomainService.validateOrder(
                        cinema,
                        failureMessages,
                        orderApprovedMessagePublisher,
                        orderRejectedMessagePublisher);
        orderApprovalRepository.save(cinema.getOrderApproval());
        return orderApprovalEvent;
    }

    private Cinema findCinema(CinemaApprovalRequest cinemaApprovalRequest) {
        Cinema Cinema = cinemaDataMapper
                .cinemaApprovalRequestToCinema(cinemaApprovalRequest);
        Optional<Cinema> CinemaResult = cinemaRepository.findCinemaInformation(Cinema);
        if (CinemaResult.isEmpty()) {
            log.error("Cinema with id " + Cinema.getId().getValue() + " not found!");
            throw new CinemaNotFoundException("Cinema with id " + Cinema.getId().getValue() +
                    " not found!");
        }

        Cinema CinemaEntity = CinemaResult.get();
        Cinema.setActive(CinemaEntity.isActive());
        Cinema.getOrderDetail().getMovies().forEach(movie ->
                CinemaEntity.getOrderDetail().getMovies().forEach(p -> {
                    if (p.getId().equals(movie.getId())) {
                        movie.updateWithConfirmedNamePriceAndAvailability(p.getName(), p.getPrice(), p.isAvailable());
                    }
                }));
        Cinema.getOrderDetail().setId(new OrderId(UUID.fromString(cinemaApprovalRequest.getOrderId())));

        return Cinema;
    }
}
