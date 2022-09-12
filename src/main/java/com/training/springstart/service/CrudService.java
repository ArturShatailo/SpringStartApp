package com.training.springstart.service;

import com.training.springstart.model.Good;

import java.util.List;

public interface CrudService<T> {

    T create(T order);

    List<T> getAll();

    T getById(Integer id);

    T updateById(Integer id, T o);

    void removeById(Integer id);
/*
    void removeAll();
*/
}
