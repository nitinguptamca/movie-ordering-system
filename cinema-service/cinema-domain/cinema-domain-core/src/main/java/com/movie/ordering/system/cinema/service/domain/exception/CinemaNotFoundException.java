package com.movie.ordering.system.cinema.service.domain.exception;

import com.movie.ordering.system.domain.exception.DomainException;

public class CinemaNotFoundException extends DomainException {
    public CinemaNotFoundException(String message) {
        super(message);
    }

    public CinemaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
