package com.impl;

import com.dao.IOrdersDao;
import com.domain.Orders;
import com.github.pagehelper.PageHelper;
import com.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }
}
