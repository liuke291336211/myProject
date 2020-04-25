package com.impl;

import com.dao.IPermissionDao;
import com.domain.Permission;
import com.github.pagehelper.PageHelper;
import com.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public void savePermission(Permission permission) {
        permissionDao.savePermission(permission);
    }

    @Override
    public List<Permission> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return permissionDao.findAll();
    }

}
