package com.training.springstart.repository;

import com.training.springstart.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@org.springframework.stereotype.Repository
@Component
public interface OrderRepository extends JpaRepository<Order, Integer> {

    //some additional and optional methods can be added here

}
