package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.cars.CreateCarBindingModel;
import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cars")
public class CarController extends BaseController {

    private final CarService carService;

    private final ModelMapper modelMapper;

    @Autowired
    public CarController(CarService carService, ModelMapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public ModelAndView createCar(@ModelAttribute CreateCarBindingModel createCarBindingModel) {
        return super.view("views/cars/create");
    }

    @PostMapping("/create")
    public ModelAndView confirmCreate(@ModelAttribute CreateCarBindingModel createCarBindingModel) {
        CarServiceModel carServiceModel = this.modelMapper.map(createCarBindingModel, CarServiceModel.class);
        this.carService.createCar(carServiceModel);
        return super.redirect("/");
    }
}