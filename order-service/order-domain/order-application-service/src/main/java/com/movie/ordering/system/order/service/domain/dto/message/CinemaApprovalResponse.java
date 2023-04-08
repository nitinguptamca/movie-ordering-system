package com.movie.ordering.system.order.service.domain.dto.message;

import com.movie.ordering.system.domain.valueobject.OrderApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CinemaApprovalResponse {
    private String id;
    private String sagaId;
    private String orderId;
    private String cinemaId;
    private Instant createdAt;
    private OrderApprovalStatus orderApprovalStatus;
    private List<String> failureMessages;
}
