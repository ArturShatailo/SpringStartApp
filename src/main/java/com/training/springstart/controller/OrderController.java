package com.training.springstart.controller;

import com.training.springstart.model.Order;
import com.training.springstart.model.dto.ClientAreaViewDTO;
import com.training.springstart.service.CrudService;
import com.training.springstart.service.order.OrderService;
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

    private final CrudService<Order> crudService;

    private final OrderService orderService;

    //Операция сохранения в базу данных
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order saveOrder(@RequestBody Order order) {
        return crudService.create(order);
    }

    //Получение списка
    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        return crudService.getAll();
    }

    //Получения по id
    @GetMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderById(@PathVariable Integer id) {
        return crudService.getById(id);
    }

    //Обновление
    @PutMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order refreshOrder(@PathVariable("id") Integer id, @RequestBody Order order) {
        return crudService.updateById(id, order);
    }

    //Удаление по id
    @PatchMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOrderById(@PathVariable Integer id) {
        crudService.removeById(id);
    }

    @GetMapping("/get-client-orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrdersByClientEmail() {

        HttpSession session = httpSessionFactory.getObject();
        ClientAreaViewDTO clientAreaViewDTO = (ClientAreaViewDTO) session.getAttribute("client");

        try {
            if (clientAreaViewDTO == null || clientAreaViewDTO.getEmail() == null)
                throw new NullPointerException();
            return orderService.getOpenByClient_email(clientAreaViewDTO.getEmail());
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            return null;
        }
    }

/*
    //Удаление всех
    @DeleteMapping("/orders")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllOrders() {
        orderService.removeAll();
    }*/
}
