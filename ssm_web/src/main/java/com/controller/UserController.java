package com.controller;


import com.domain.UserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.impl.UserServiceImpl;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") int page,
                                @RequestParam(name = "size", required = true, defaultValue = "4") int size){
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
}
