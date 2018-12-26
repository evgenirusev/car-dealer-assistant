package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.users.CreateUserBindingModel;
import com.cardealership.domain.model.service.users.UserServiceModel;
import com.cardealership.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.validation.Valid;

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
        return super.view("views/users/login");
    }

    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute CreateUserBindingModel createUserBindingModel) {
        return super.view("views/users/register");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute CreateUserBindingModel createUserBindingModel, BindingResult bindingResult) {

        System.out.println("aaaa");

        if (bindingResult.hasErrors()) {
            return super.view("views/users/register", "Register");
        }

        UserServiceModel userServiceModel = this.modelMapper.map(createUserBindingModel, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);
        return super.redirect("login");
    }
}