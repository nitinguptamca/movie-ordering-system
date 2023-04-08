package com.movie.ordering.system.payment.service.domain.valueobject;

import com.movie.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

public class CreditEntryId extends BaseId<UUID> {
    public CreditEntryId(UUID value) {
        super(value);
    }
}
