package com.dao;

import com.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleDao {

    //根据用户的id查询出对应的觉角色
    @Select("select * from role where id in (select roleId from users_role where userId = #{id})")
    List<Role> findRoleByUserId(int id);
}
