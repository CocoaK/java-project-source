package com.quhwa.scalehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/demo")
public class IndexController {

    @RequestMapping
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("index");
        view.addObject("welcome", "hello");
        return view;
    }

}
