package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.cars.CreateCarBindingModel;
import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.service.parts.PartServiceModel;
import com.cardealership.domain.model.view.cars.CarBrandsViewModel;
import com.cardealership.domain.model.view.parts.PartsForCreatingCarModel;
import com.cardealership.service.CarService;
import com.cardealership.service.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cars")
public class CarController extends BaseController {

    private final CarService carService;

    private final ModelMapper modelMapper;

    private final PartService partService;

    @Autowired
    public CarController(CarService carService, ModelMapper modelMapper, PartService partService) {
        this.carService = carService;
        this.modelMapper = modelMapper;
        this.partService = partService;
    }

    @GetMapping("/create")
    public ModelAndView createCarWithParts(@ModelAttribute CreateCarBindingModel createCarBindingModel) {
        List<PartsForCreatingCarModel> partsForCreatingCarModels = this.partService.findAllPartsForCreatingCar();
        return super.view("/views/cars/create", partsForCreatingCarModels);
    }

    @PostMapping("/create")
    public ModelAndView confirmCreateCarWithParts(@ModelAttribute CreateCarBindingModel carBindingModel) {
        CarServiceModel carWithPartsServiceModel = this.modelMapper.map(carBindingModel, CarServiceModel.class);
        Set<PartServiceModel> partModels = new HashSet<>();
        carBindingModel.getPartIds().forEach(partId -> {
            PartServiceModel partModel = this.partService.findPartById(partId);
            partModels.add(partModel);
        });
        carWithPartsServiceModel.setParts(partModels);
        this.carService.createCar(carWithPartsServiceModel);
        return super.redirect("/");
    }

    @GetMapping("/brands")
    public ModelAndView carBrands() {
        List<CarBrandsViewModel> viewModels = new ArrayList<>();
        List<CarServiceModel> carServiceModels = this.carService.findCarsAsc();
        carServiceModels.forEach(serviceModel -> {
            viewModels.add(this.modelMapper.map(serviceModel, CarBrandsViewModel.class));
        });
        return super.view("/views/cars/brands", viewModels);
    }
}