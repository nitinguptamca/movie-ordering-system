package com.movie.ordering.system.cinema.service.domain.ports.output.repository;

import com.movie.ordering.system.cinema.service.domain.entity.OrderApproval;

public interface OrderApprovalRepository {
    OrderApproval save(OrderApproval orderApproval);
}
