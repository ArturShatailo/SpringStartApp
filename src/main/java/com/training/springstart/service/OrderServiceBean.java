package com.training.springstart.service;

import com.training.springstart.entity.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.training.springstart.repository.OrderRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class OrderServiceBean implements CrudService<Order> {

    private final OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id = " + id));
                //.orElseThrow(ResourceNotFoundException::new);
         if (order.isDeleted()) {
            throw new EntityNotFoundException("Order was deleted with id = " + id);
        }
        return order;
    }

    @Override
    public Order updateById(Integer id, Order order) {
        return orderRepository.findById(id)
                .map(entity -> {
                    entity.setArticle(order.getArticle());
                    entity.setAmount(order.getAmount());
                    entity.setClient_id(order.getClient_id());
                    entity.setDelivery_method(order.getDelivery_method());
                    entity.setPayment_method(order.getPayment_method());
                    return orderRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id = " + id));
    }

    @Override
    public void removeById(Integer id) {
        //repository.delete(employee);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id = " + id));
                /*.orElseThrow(ResourceWasDeletedException::new)*/;
        order.setDeleted(true);
        orderRepository.save(order);
    }
/*
    @Override
    public void removeAll() {
        repository.deleteAll();

    }
*/
}
