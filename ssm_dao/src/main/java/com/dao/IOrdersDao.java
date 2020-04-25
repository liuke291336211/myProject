package com.dao;

import com.domain.Orders;
import com.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id= true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            //多表查询的实现方法
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.dao.IProductDao.findById")),
    })
    List<Orders> findAll();

    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id= true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            //多表查询的实现方法
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId", javaType = Product.class, one = @One(select = "com.dao.IMemberDao.findById")),
            @Result(property = "travellers", column = "id", javaType = List.class, many = @Many(select = "com.dao.ITravellerDao.findByOrdersId")),

    })
    Orders findById(Integer id);
}
