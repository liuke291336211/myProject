package com.dao;

import com.domain.Permission;
import com.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    //根据用户的id查询出对应的觉角色
    @Select("select * from role where id in (select roleId from users_role where userId = #{id})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
    })

    List<Role> findRoleByUserId(Integer id);
    @Select("select * from role where id in (select roleId from users_role where userId = #{id})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = List.class, many = @Many(select = "com.dao.IPermissionDao.findByRoleId")),
    })

    List<Role> findRoleByUserIdDetail(Integer id);

    @Select("select * from role")
    List<Role> findAll();

    @Select("insert Integero role(roleName, roleDesc) values(#{roleName},#{roleDesc})")
    void saveRole(Role role);

    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = List.class, many = @Many(select = "com.dao.IPermissionDao.findByRoleId"))
    })
    Role findById(Integer id);

    @Delete("delete from users_role where roleId = #{id}")
    void delete_User_Role(Integer id);

    @Delete("delete from role_permission where roleId = #{id}")
    void delete_Role_Permission(Integer id);

    @Delete("delete from role where id = #{id}")
    void deleteRoleById(Integer id);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermission(Integer roleId);

    @Insert("insert Integero role_permission(permissionId, roleId) values(#{permissionId}, #{roleId})")
    void addPermissionToRole(@Param("roleId") Integer roleId,@Param("permissionId") Integer permissionId);
}
