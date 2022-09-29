package com.training.springstart.repository;

import com.training.springstart.model.Client;
import com.training.springstart.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Repository
@Component
public interface OrderRepository extends JpaRepository<Order, Integer>, PagingAndSortingRepository<Order, Integer> {

    //some additional and optional methods can be added here

    @Query(value = "SELECT * FROM order_requests o WHERE o.client_email=?1 AND status='open' AND deleted=false", nativeQuery = true)
    List<Order> findOpenOrdersByClient_email(String email);

}
