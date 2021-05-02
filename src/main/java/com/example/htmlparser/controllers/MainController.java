package com.example.htmlparser.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping(value = "/")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");

        return modelAndView;
    }
}
