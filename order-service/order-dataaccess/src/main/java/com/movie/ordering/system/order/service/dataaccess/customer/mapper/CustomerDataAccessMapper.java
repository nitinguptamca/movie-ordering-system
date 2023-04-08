package com.movie.ordering.system.order.service.dataaccess.customer.mapper;

import com.movie.ordering.system.domain.valueobject.CustomerId;
import com.movie.ordering.system.order.service.dataaccess.customer.entity.CustomerEntity;
import com.movie.ordering.system.order.service.domain.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }
}
