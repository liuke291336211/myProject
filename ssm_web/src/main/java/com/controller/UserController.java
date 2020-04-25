package com.controller;


import com.domain.Role;
import com.domain.UserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.impl.UserServiceImpl;
import com.service.IUserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.ModalExclude;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "size", required = true, defaultValue = "4") Integer size){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfos = userService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(userInfos);
        mv.addObject("userList",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("/saveUser.do")
    public String saveUser(UserInfo userInfo){
        userService.saveUser(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(Integer id) {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo =  userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndRole.do")
    public ModelAndView findUserByIdAndRole(@RequestParam(name = "id", required = true) Integer userId) {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(userId);
        List<Role> otherRoles = userService.findOtherRoles(userId);
        mv.addObject("user", user);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-addRole");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) Integer userId,
                                @RequestParam(name = "ids", required = true) Integer[] roleIds) {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }
}
