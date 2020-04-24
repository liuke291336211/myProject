package com.service;

import com.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll(int page, int size);

    void saveUser(UserInfo userInfo);
}
