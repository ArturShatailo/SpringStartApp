package com.training.springstart.service.order;

import com.training.springstart.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOpenByClient_email(String email);

}
