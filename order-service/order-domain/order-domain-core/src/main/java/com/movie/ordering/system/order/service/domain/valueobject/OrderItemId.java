package com.movie.ordering.system.order.service.domain.valueobject;

import com.movie.ordering.system.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}
