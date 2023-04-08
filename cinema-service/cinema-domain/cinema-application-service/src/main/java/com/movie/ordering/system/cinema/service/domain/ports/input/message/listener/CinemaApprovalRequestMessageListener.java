package com.movie.ordering.system.cinema.service.domain.ports.input.message.listener;

import com.movie.ordering.system.cinema.service.domain.dto.CinemaApprovalRequest;

public interface CinemaApprovalRequestMessageListener {
    void approveOrder(CinemaApprovalRequest cinemaApprovalRequest);
}
