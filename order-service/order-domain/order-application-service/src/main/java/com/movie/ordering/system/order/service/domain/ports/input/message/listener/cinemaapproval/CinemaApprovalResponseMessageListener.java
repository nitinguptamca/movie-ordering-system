package com.movie.ordering.system.order.service.domain.ports.input.message.listener.cinemaapproval;

import com.movie.ordering.system.order.service.domain.dto.message.CinemaApprovalResponse;

public interface CinemaApprovalResponseMessageListener {

    void orderApproved(CinemaApprovalResponse cinemaApprovalResponse);

    void orderRejected(CinemaApprovalResponse cinemaApprovalResponse);
}
