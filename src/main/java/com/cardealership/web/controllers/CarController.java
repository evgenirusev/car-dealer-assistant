package com.cardealership.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cars")
public class CarController extends BaseController {

    @GetMapping("/create")
    public ModelAndView createCar() {
        return super.view("views/cars/create");
    }

}