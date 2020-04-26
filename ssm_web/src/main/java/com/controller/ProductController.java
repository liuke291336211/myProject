package com.controller;

import com.domain.Product;
import com.github.pagehelper.PageInfo;
import com.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    //查询全部产品
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "size", required = true, defaultValue = "4") Integer size){
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(ps);
        mv.addObject("productList", pageInfo);
        mv.setViewName("product-list");
        return mv;
    }
    @RequestMapping("/saveProduct.do")
    public String saveProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:findAll.do";
    }

}
