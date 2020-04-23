package com.service;

import com.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll(int page, int size);

    Orders findById(int id);
}
