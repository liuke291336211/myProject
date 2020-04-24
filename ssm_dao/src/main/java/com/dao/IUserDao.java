package com.dao;

import com.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users")
    List<UserInfo> findAll();

    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "com.dao.IRoleDao.findRoleByUserId"))

    })
    UserInfo findByUserName(String username);

    @Insert("insert into users(email, username, password, phoneNum, status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void saveUser(UserInfo userInfo);
}
