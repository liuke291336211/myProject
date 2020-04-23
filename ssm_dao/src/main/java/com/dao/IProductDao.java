package com.dao;

import com.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IProductDao {

    @Select("select * from product")
    List<Product> findAll();

    @Insert("INSERT INTO product(productName, cityName, DepartureTime, productPrice,productDesc, productStatus) " +
            "VALUES(#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void saveProduct(Product product);

    //根据id查询产品
    @Select("select * from product where productNum = #{id}")
    public Product findById(int id);
}
