package com.controller;

import com.dao.IOrdersDao;
import com.domain.Orders;
import com.github.pagehelper.PageInfo;
import com.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;
    //未分页
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() {
//        ModelAndView mv = new ModelAndView();
//        List<Orders> orders = ordersService.findAll();
//        mv.addObject("ordersList", orders);
//        mv.setViewName("orders-list");
//        return mv;
//    }
    //已分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1")int page,
                                @RequestParam(name = "size", required = true, defaultValue = "4") int size) {

        ModelAndView mv = new ModelAndView();
        List<Orders> orders = ordersService.findAll(page, size);

        //分页bean通过构造方法重新封装
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    //详情查询
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) int id) {
        ModelAndView mv = new ModelAndView();
        Orders orders =  ordersService.findById(id);
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
