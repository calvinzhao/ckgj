package com.ckgj.controller;

import com.ckgj.models.user.User;
import com.ckgj.models.user.UserForm;
import com.ckgj.services.company.CompanyService;
import com.ckgj.services.user.UserCreateFormValidator;
import com.ckgj.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

/**
 * Created by calvin on 3/25/16.
 */

@Controller
@RequestMapping("/admin/user")
public class UsersController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;
    private final CompanyService companyService;

    @Autowired
    public UsersController(UserService userService, UserCreateFormValidator userCreateFormValidator, CompanyService companyService) {
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
        this.companyService = companyService;
    }

    @RequestMapping
    public ModelAndView getUsersPage() {
        return new ModelAndView("user/users", "users", userService.getAllUsers());
    }

    @RequestMapping("{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        return new ModelAndView("user/view", "user", userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        ModelAndView modelAndView = new ModelAndView("user/user_create", "userForm", new UserForm());
        modelAndView.addObject("companies", companyService.findAll());
        return modelAndView;
    }

    @InitBinder("userForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ModelAndView handleUserCreateForm(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("user/user_create", "formErrors", bindingResult.getAllErrors());
            modelAndView.addObject("companies", companyService.findAll());
            return modelAndView;
        }
        try {
            userService.createOrUpdate(userForm);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("phone.exists", "phone already exists");
            ModelAndView modelAndView = new ModelAndView("user/user_create", "formErrors", bindingResult.getAllErrors());
            modelAndView.addObject("companies", companyService.findAll());
            return modelAndView;
        }
        return new ModelAndView("redirect:");
    }

    @RequestMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);
        Iterable<User> users = this.userService.getAllUsers();
        return new ModelAndView("user/users", "users", users);
    }

    @RequestMapping(value = "modify/{id}", method = RequestMethod.GET)
    public ModelAndView modifyForm(@PathVariable long id) {
        UserForm userForm = new UserForm(userService.getUserById(id).orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
        ModelAndView modelAndView = new ModelAndView("user/user_create", "userForm", userForm);
        modelAndView.addObject("companies", companyService.findAll());
        return modelAndView;
    }
}
