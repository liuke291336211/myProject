package com.service;

import com.domain.Permission;
import com.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll(Integer page, Integer size);

    void saveRole(Role role);

    Role findById(Integer id);

    void deleteRoleById(Integer id);

    List<Permission> findOtherPermission(Integer roleId);

    void addPermissionToRole(Integer roleId, Integer[] permissionIds);
}
