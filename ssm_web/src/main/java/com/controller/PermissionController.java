package com.controller;


import com.domain.Permission;
import com.github.pagehelper.PageInfo;
import com.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1")Integer page,
                                @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findAll(page, size);
        PageInfo permission = new PageInfo(permissions);
        mv.addObject("permissionList", permission);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/savePermission.do")
    public String savePermission(Permission permission){
        permissionService.savePermission(permission);
        return "redirect:findAll.do";
    }

}
