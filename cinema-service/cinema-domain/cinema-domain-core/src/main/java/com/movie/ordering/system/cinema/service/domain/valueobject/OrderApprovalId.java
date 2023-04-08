package com.movie.ordering.system.cinema.service.domain.valueobject;

import com.movie.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

public class OrderApprovalId extends BaseId<UUID> {
    public OrderApprovalId(UUID value) {
        super(value);
    }
}
