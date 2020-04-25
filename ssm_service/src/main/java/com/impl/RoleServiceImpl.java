package com.impl;

import com.dao.IRoleDao;
import com.domain.Permission;
import com.domain.Role;
import com.github.pagehelper.PageHelper;
import com.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<Role> findAll(Integer page, Integer size) {

        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionIds) {
        for (int permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId, permissionId);
        }
    }

    @Override
    public List<Permission> findOtherPermission(Integer roleId) {
        return roleDao.findOtherPermission(roleId);
    }

    @Override
    public void deleteRoleById(Integer id) {
        roleDao.delete_User_Role(id);
        roleDao.delete_Role_Permission(id);
        roleDao.deleteRoleById(id);
    }

    @Override
    public Role findById(Integer id) {
        return roleDao.findById(id);
    }
}
