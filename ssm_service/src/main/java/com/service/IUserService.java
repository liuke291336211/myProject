package com.service;

import com.domain.Role;
import com.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll(Integer page, Integer size);

    void saveUser(UserInfo userInfo);

    UserInfo findById(Integer id);

    List<Role> findOtherRoles(Integer userId);

    void addRoleToUser(Integer userId, Integer[] roleIds);
}
