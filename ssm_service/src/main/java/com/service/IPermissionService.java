package com.service;


import com.domain.Permission;

import java.util.List;

public interface IPermissionService {


    List<Permission> findAll(Integer page, Integer size);

    void savePermission(Permission permission);
}
