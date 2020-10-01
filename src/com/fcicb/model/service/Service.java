package com.fcicb.model.service;

import java.util.List;

public interface Service <T>{

    boolean add(T item);
    boolean add(List<T> items);

    T getById(int id);
    List<T> getAll();

    boolean update(T item);

    boolean delete(T item);
}
