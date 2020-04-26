package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/main")
public class MainController {

    @RequestMapping("/index.do")
    public ModelAndView forword() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");
        return mv;
    }
}
