package com.impl;

import com.dao.IProductDao;
import com.domain.Product;
import com.github.pagehelper.PageHelper;
import com.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;
    @Override
    public List<Product> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        return productDao.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }
}
