package com.movie.ordering.system.domain.event;

public interface DomainEvent<T> {
    void fire();
}
