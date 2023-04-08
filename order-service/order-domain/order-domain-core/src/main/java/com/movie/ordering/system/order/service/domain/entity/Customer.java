package com.movie.ordering.system.order.service.domain.entity;

import com.movie.ordering.system.domain.entity.AggregateRoot;
import com.movie.ordering.system.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {
    public Customer() {
    }

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }
}
