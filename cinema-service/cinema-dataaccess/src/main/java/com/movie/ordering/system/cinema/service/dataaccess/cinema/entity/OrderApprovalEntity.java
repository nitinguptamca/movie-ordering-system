package com.movie.ordering.system.cinema.service.dataaccess.cinema.entity;

import com.movie.ordering.system.domain.valueobject.OrderApprovalStatus;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_approval", schema = "cinema")
@Entity
public class OrderApprovalEntity {

    @Id
    private UUID id;
    private UUID cinemaId;
    private UUID orderId;
    @Enumerated(EnumType.STRING)
    private OrderApprovalStatus status;
}
