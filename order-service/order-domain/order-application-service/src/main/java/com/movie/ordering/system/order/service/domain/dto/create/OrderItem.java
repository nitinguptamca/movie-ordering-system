package com.movie.ordering.system.order.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderItem {
    @NotNull
    private final UUID movieId;
    @NotNull
    private final Integer quantity;
    @NotNull
    private final BigDecimal price;
    @NotNull
    private final BigDecimal subTotal;
}
