package com.movie.ordering.system.payment.service.domain.ports.output.repository;

import com.movie.ordering.system.domain.valueobject.CustomerId;
import com.movie.ordering.system.payment.service.domain.entity.CreditEntry;

import java.util.Optional;

public interface CreditEntryRepository {

    CreditEntry save(CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId(CustomerId customerId);
}
