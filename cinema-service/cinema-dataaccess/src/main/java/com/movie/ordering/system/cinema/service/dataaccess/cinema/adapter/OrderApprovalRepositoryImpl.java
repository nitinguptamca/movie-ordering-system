package com.movie.ordering.system.cinema.service.dataaccess.cinema.adapter;

import com.movie.ordering.system.cinema.service.dataaccess.cinema.mapper.CinemaDataAccessMapper;
import com.movie.ordering.system.cinema.service.dataaccess.cinema.repository.OrderApprovalJpaRepository;
import com.movie.ordering.system.cinema.service.domain.entity.OrderApproval;
import com.movie.ordering.system.cinema.service.domain.ports.output.repository.OrderApprovalRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderApprovalRepositoryImpl implements OrderApprovalRepository {

    private final OrderApprovalJpaRepository orderApprovalJpaRepository;
    private final CinemaDataAccessMapper cinemaDataAccessMapper;

    public OrderApprovalRepositoryImpl(OrderApprovalJpaRepository orderApprovalJpaRepository,
                                       CinemaDataAccessMapper cinemaDataAccessMapper) {
        this.orderApprovalJpaRepository = orderApprovalJpaRepository;
        this.cinemaDataAccessMapper = cinemaDataAccessMapper;
    }

    @Override
    public OrderApproval save(OrderApproval orderApproval) {
        return cinemaDataAccessMapper
                .orderApprovalEntityToOrderApproval(orderApprovalJpaRepository
                        .save(cinemaDataAccessMapper.orderApprovalToOrderApprovalEntity(orderApproval)));
    }

}
