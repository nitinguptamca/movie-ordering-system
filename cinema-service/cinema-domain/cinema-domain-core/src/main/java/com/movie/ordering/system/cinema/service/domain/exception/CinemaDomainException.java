package com.movie.ordering.system.cinema.service.domain.exception;

import com.movie.ordering.system.domain.exception.DomainException;

public class CinemaDomainException extends DomainException {
    public CinemaDomainException(String message) {
        super(message);
    }

    public CinemaDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
