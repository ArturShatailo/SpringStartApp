package com.training.springstart.controller;

import com.training.springstart.model.Client;
import com.training.springstart.model.Order;
import com.training.springstart.model.dto.ClientAreaViewDTO;
import com.training.springstart.service.CrudService;
import com.training.springstart.service.order.OrderService;
import com.training.springstart.service.order.OrderServiceBean;
import com.training.springstart.util.PagingEntity.PagingEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private final ObjectFactory<HttpSession> httpSessionFactory;

    private final OrderServiceBean orderServiceBean;

    //Операция сохранения в базу данных
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order saveOrder(@RequestBody Order order) {
        return orderServiceBean.create(order);
    }

    //Получение списка
    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        return orderServiceBean.getAll();
    }

    //Получения по id
    @GetMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderById(@PathVariable Integer id) {
        return orderServiceBean.getById(id);
    }

    //Обновление
    @PutMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order refreshOrder(@PathVariable("id") Integer id, @RequestBody Order order) {
        return orderServiceBean.updateById(id, order);
    }

    //Удаление по id
    @PatchMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOrderById(@PathVariable Integer id) {
        orderServiceBean.removeById(id);
    }

    @GetMapping("/get-client-orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrdersByClientEmail() {

        HttpSession session = httpSessionFactory.getObject();
        Client client = (Client) session.getAttribute("client");

        try {
            if (client == null || client.getEmail() == null)
                throw new NullPointerException();
            return orderServiceBean.getOpenByClient_email(client.getEmail());
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/orders/table/", params = {"page", "size", "sort"})
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getClientsPage(@RequestParam(defaultValue = "0") Integer page,
                                       @RequestParam(defaultValue = "10") Integer size,
                                       @RequestParam(defaultValue = "id") String sort) {

        PagingEntity pagingEntity = new PagingEntity(page, size, sort);
        return orderServiceBean.getPageAllNotDeleted(pagingEntity);
    }

/*
    //Удаление всех
    @DeleteMapping("/orders")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllOrders() {
        orderService.removeAll();
    }*/
}
