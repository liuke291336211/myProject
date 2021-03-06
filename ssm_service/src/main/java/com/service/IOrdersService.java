package com.service;

import com.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll(Integer page, Integer size);

    Orders findById(Integer id);
}
