package com.movie.ordering.system.saga;

import com.movie.ordering.system.domain.event.DomainEvent;

public interface SagaStep<T, S extends DomainEvent, U extends DomainEvent> {
    S process(T data);

    U rollback(T data);
}
