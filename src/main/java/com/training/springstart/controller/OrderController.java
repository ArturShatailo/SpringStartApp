package com.training.springstart.controller;

import com.training.springstart.entity.Order;
import com.training.springstart.service.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private final CrudService<Order> crudService;

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

/*
    //Удаление всех
    @DeleteMapping("/orders")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllOrders() {
        orderService.removeAll();
    }*/
}
