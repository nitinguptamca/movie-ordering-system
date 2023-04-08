package com.movie.ordering.system.cinema.service.domain;

import com.movie.ordering.system.cinema.service.domain.dto.CinemaApprovalRequest;
import com.movie.ordering.system.cinema.service.domain.event.OrderApprovalEvent;
import com.movie.ordering.system.cinema.service.domain.ports.input.message.listener.CinemaApprovalRequestMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CinemaApprovalRequestMessageListenerImpl implements CinemaApprovalRequestMessageListener {

    private final CinemaApprovalRequestHelper cinemaApprovalRequestHelper;

    public CinemaApprovalRequestMessageListenerImpl(CinemaApprovalRequestHelper
                                                            cinemaApprovalRequestHelper) {
        this.cinemaApprovalRequestHelper = cinemaApprovalRequestHelper;
    }

    @Override
    public void approveOrder(CinemaApprovalRequest cinemaApprovalRequest) {
        OrderApprovalEvent orderApprovalEvent =
                cinemaApprovalRequestHelper.persistOrderApproval(cinemaApprovalRequest);
        orderApprovalEvent.fire();
    }
}
