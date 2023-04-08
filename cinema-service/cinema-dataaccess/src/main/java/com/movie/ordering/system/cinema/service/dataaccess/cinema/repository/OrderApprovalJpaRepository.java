package com.movie.ordering.system.cinema.service.dataaccess.cinema.repository;

import com.movie.ordering.system.cinema.service.dataaccess.cinema.entity.OrderApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderApprovalJpaRepository extends JpaRepository<OrderApprovalEntity, UUID> {


}
