package com.service;

import com.domain.Product;

import java.util.List;

public interface IProductService {


    List<Product> findAll(int page, int size);

    void saveProduct(Product product);
}
