package com.controller;


import com.dao.IPermissionDao;
import com.domain.Permission;
import com.domain.Role;
import com.github.pagehelper.PageInfo;
import com.service.IPermissionService;
import com.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(roles);
        mv.addObject("roles", pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/saveRole.do")
    public String saveRole(Role role) {
        roleService.saveRole(role);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(Integer id) {
        ModelAndView mv = new ModelAndView();
        Role role =  roleService.findById(id);
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/deleteById.do")
    public String deleteRoleById(Integer id) {
        roleService.deleteRoleById(id);

        return "redirect:findAll.do";
    }
    @RequestMapping("/findAllPermission.do")
    public ModelAndView findAllPermission(Integer roleId) {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        List<Permission> permissions = roleService.findOtherPermission(roleId);
        mv.addObject("roles", role);
        mv.addObject("permissionOther", permissions);
        mv.setViewName("role-addPermission");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "RoleId", required = true) Integer roleId,
                                      @RequestParam(name = "ids", required = true) Integer[] permissionIds){
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }
}
