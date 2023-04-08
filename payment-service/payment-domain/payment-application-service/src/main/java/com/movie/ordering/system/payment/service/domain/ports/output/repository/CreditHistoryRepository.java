package com.movie.ordering.system.payment.service.domain.ports.output.repository;

import com.movie.ordering.system.domain.valueobject.CustomerId;
import com.movie.ordering.system.payment.service.domain.entity.CreditHistory;

import java.util.List;
import java.util.Optional;

public interface CreditHistoryRepository {

    CreditHistory save(CreditHistory creditHistory);

    Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);
}
