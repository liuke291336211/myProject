package com.service;

import com.domain.Product;

import java.util.List;

public interface IProductService {


    List<Product> findAll(Integer page, Integer size);

    void saveProduct(Product product);
}
