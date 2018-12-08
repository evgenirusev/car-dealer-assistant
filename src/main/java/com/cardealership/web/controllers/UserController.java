package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.RegisterUserBindingModel;
import com.cardealership.domain.model.service.UserServiceModel;
import com.cardealership.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/login")
    public ModelAndView login() {
        return super.view("view/users/login");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return super.view("view/users/register");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute RegisterUserBindingModel registerUserBindingModel) {
        UserServiceModel userServiceModel = this.modelMapper.map(registerUserBindingModel, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);
        return super.redirect("login");
    }
}