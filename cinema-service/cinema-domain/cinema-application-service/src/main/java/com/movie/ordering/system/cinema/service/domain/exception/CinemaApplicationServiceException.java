package com.movie.ordering.system.cinema.service.domain.exception;

import com.movie.ordering.system.domain.exception.DomainException;

public class CinemaApplicationServiceException extends DomainException {
    public CinemaApplicationServiceException(String message) {
        super(message);
    }

    public CinemaApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
