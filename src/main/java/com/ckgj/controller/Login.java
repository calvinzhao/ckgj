package com.ckgj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by calvin on 3/2
 */
@Controller
public class Login {
    @RequestMapping(name="/login", method=RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(name="/login", method=RequestMethod.POST)
    public ModelAndView signin() {
        return new ModelAndView("login");
    }
}
