package com.ckgj.controller;

/**
 * Created by calvin on 3/23/16.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloGreeting {
    @RequestMapping("/greeting")
    public ModelAndView greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        // model.addAttribute("name", name);
        return new ModelAndView("greeting", "name", name);
    }
}
