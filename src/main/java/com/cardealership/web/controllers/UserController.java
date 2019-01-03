package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.users.CreateUserBindingModel;
import com.cardealership.domain.model.service.users.UserServiceModel;
import com.cardealership.service.RecaptchaService;
import com.cardealership.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    private final RecaptchaService recaptchaService;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, RecaptchaService recaptchaService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.recaptchaService = recaptchaService;
    }

    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute CreateUserBindingModel createUserBindingModel) {
        return super.view("views/users/register");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute CreateUserBindingModel createUserBindingModel,
                                        BindingResult bindingResult,
                                        @RequestParam(name = "g-recaptcha-response") String gRecaptchaResponse,
                                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return super.view("views/users/register", "Register");
        }

        if (!this.recaptchaService.verifyRecaptcha(request.getRemoteAddr(), gRecaptchaResponse).equals("success")) {
            return super.redirect("/register");
        }

        UserServiceModel userServiceModel = this.modelMapper.map(createUserBindingModel, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);
        return super.redirect("login");
    }

    @GetMapping("/login")
    public ModelAndView login(String error, ModelAndView mav) {
        mav.addObject("viewName", "/views/users/login");
        mav.setViewName("layout");
        if (error != null) {
            mav.addObject("error", "Wrong username or password");
        }
        return mav;
    }
}