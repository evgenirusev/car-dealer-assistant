package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.cars.CreateCarBindingModel;
import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.service.parts.PartServiceModel;
import com.cardealership.domain.model.view.cars.CarBrandsViewModel;
import com.cardealership.domain.model.view.cars.CarViewModel;
import com.cardealership.domain.model.view.cars.CarWithPartsViewModel;
import com.cardealership.domain.model.view.parts.PartsForCreatingCarViewModel;
import com.cardealership.service.CarService;
import com.cardealership.service.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    private List<PartsForCreatingCarViewModel> partsCache;

    @Autowired
    public CarController(CarService carService, ModelMapper modelMapper, PartService partService) {
        this.carService = carService;
        this.modelMapper = modelMapper;
        this.partService = partService;
    }

    @GetMapping("/create")
    public ModelAndView createCarWithParts(@ModelAttribute CreateCarBindingModel createCarBindingModel) {
        List<PartsForCreatingCarViewModel> partsForCreatingCar = new ArrayList<>();
        List<PartServiceModel> partServiceModels = this.partService.findAll();

        if (!partServiceModels.isEmpty()) {
            partServiceModels.forEach(partServiceModel -> {
                PartsForCreatingCarViewModel partsForCreatingCarViewModel
                        = this.modelMapper.map(partServiceModel, PartsForCreatingCarViewModel.class);
                partsForCreatingCar.add(partsForCreatingCarViewModel);
            });
        }

        if (this.partsCache == null) {
            this.partsCache = partsForCreatingCar;
        }

        return super.view("/views/cars/create", partsForCreatingCar);
    }

    @PostMapping("/create")
    public ModelAndView confirmCreateCarWithParts(@Valid @ModelAttribute CreateCarBindingModel carBindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return super.view("views/cars/create", this.partsCache, "Create Car");
        }

        CarServiceModel carWithPartsServiceModel = this.modelMapper.map(carBindingModel, CarServiceModel.class);
        Set<PartServiceModel> partModels = new HashSet<>();
        carBindingModel.getPartIds().forEach(partId -> {
            PartServiceModel partModel = this.partService.findById(partId);
            partModels.add(partModel);
        });

        carWithPartsServiceModel.setParts(partModels);
        this.carService.createCar(carWithPartsServiceModel);
        this.partsCache.clear();
        return super.redirect("/cars/brands");
    }

    @GetMapping("/brands")
    public ModelAndView carBrands() {
        List<CarBrandsViewModel> viewModels = new ArrayList<>();
        List<CarServiceModel> carServiceModels = this.carService.findAsc();
        carServiceModels.forEach(serviceModel -> {
            viewModels.add(this.modelMapper.map(serviceModel, CarBrandsViewModel.class));
        });
        return super.view("/views/cars/brands", viewModels);
    }

    @GetMapping("/{brand}")
    public ModelAndView getAllAscendingOrder(@PathVariable(name = "brand") String brand) {
        List<CarServiceModel> carServiceModels = this.carService.findCarsByBrandOrderedByModelAscAndDistanceDesc(brand);
        List<CarViewModel> carViewModels = new ArrayList<>();
        carServiceModels.forEach(serviceModel -> {
            CarViewModel carViewModel = this.modelMapper.map(serviceModel, CarViewModel.class);
            carViewModels.add(carViewModel);
        });
        return super.view("views/cars/by-brand", carViewModels);
    }

    @GetMapping("/{id}/parts")
    public ModelAndView getCarWithParts(@PathVariable(name = "id") long id) {
        CarServiceModel serviceModel = this.carService.findById(id);
        CarWithPartsViewModel carWithPartsViewModel = this.modelMapper.map(serviceModel, CarWithPartsViewModel.class);
        return super.view("/views/cars/car-with-parts", carWithPartsViewModel);
    }
}