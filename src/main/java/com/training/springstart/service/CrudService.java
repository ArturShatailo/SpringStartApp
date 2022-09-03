package com.training.springstart.service;

import com.training.springstart.entity.Order;

import java.util.List;

public interface CrudService<T> {

    T create(T order);

    List<T> getAll();

    T getById(Integer id);

    T updateById(Integer id, T order);

    void removeById(Integer id);
/*
    void removeAll();
*/
}
