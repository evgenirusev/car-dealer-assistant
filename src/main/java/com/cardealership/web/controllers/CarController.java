package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.cars.CreateCarBindingModel;
import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.service.parts.PartServiceModel;
import com.cardealership.domain.model.view.parts.PartsForCreatingCarModel;
import com.cardealership.service.CarService;
import com.cardealership.service.PartService;
import org.hibernate.collection.internal.PersistentSet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView confirmCreateCarWithParts(@ModelAttribute CreateCarBindingModel carServiceModel) {
        CarServiceModel carWithPartsServiceModel = this.modelMapper.map(carServiceModel, CarServiceModel.class);

        Set<PartServiceModel> parts = new HashSet<>();

        carServiceModel.getPartIds().forEach(partId -> {
            PartServiceModel partServiceModel = this.partService.findPartById(Long.valueOf(partId));
            partServiceModel.addCar(carWithPartsServiceModel);
            parts.add(partServiceModel);
        });

        carWithPartsServiceModel.setParts(parts);

        this.carService.createCar(carWithPartsServiceModel);
        return super.redirect("/");
    }
}