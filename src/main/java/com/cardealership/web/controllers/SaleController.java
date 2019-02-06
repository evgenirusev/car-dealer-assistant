package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.cars.CreateCarBindingModel;
import com.cardealership.domain.model.binding.sale.CreateSaleBindingModel;
import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.service.customers.CustomerServiceModel;
import com.cardealership.domain.model.service.sales.SaleDetailsServiceModel;
import com.cardealership.domain.model.service.sales.SaleServiceModel;
import com.cardealership.domain.model.view.cars.CarForCreatingSaleViewModel;
import com.cardealership.domain.model.view.customers.CustomerForCreatingSaleViewModel;
import com.cardealership.domain.model.view.sales.CreateReviewViewModel;
import com.cardealership.domain.model.view.sales.CreateSaleViewModel;
import com.cardealership.domain.model.view.sales.SaleDetailsViewModel;
import com.cardealership.domain.model.view.sales.SaleViewModel;
import com.cardealership.service.CarService;
import com.cardealership.service.CustomerService;
import com.cardealership.service.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sales")
public class SaleController extends BaseController{

    private final CustomerService customerService;

    private final CarService carService;

    private final SaleService saleService;

    private final ModelMapper modelMapper;

    private Map<String, Object> cache;

    public SaleController(CustomerService customerService, CarService carService, SaleService saleService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.carService = carService;
        this.saleService = saleService;
        this.modelMapper = modelMapper;
        this.cache = new HashMap<>();
    }

    @GetMapping("/create")
    public ModelAndView createSale(@ModelAttribute CreateSaleBindingModel createSaleBindingModel) {
        this.cache.clear();
        CreateSaleViewModel createSaleViewModel = new CreateSaleViewModel();
        createSaleViewModel.setCreateSaleBindingModel(createSaleBindingModel);

        List<CarForCreatingSaleViewModel> carModels = new ArrayList<>();
        List<CarServiceModel> carServiceModels = this.carService.findAll();
        if (!carServiceModels.isEmpty()) {
            carServiceModels.forEach(carServiceModel -> {
                CarForCreatingSaleViewModel carViewModel = this.modelMapper.map(carServiceModel, CarForCreatingSaleViewModel.class);
                carModels.add(carViewModel);
            });
        }

        createSaleViewModel.setCarsForCreatingSaleList(carModels);

        List<CustomerForCreatingSaleViewModel> customerModels = new ArrayList<>();
        List<CustomerServiceModel> customerServiceModels = this.customerService.findAllOrderByBirthDateAsc();
        if (!customerServiceModels.isEmpty()) {
            customerServiceModels.forEach(customerServiceModel -> {
                CustomerForCreatingSaleViewModel customerViewModel = this.modelMapper.map(customerServiceModel, CustomerForCreatingSaleViewModel.class);
                customerModels.add(customerViewModel);
            });
        }

        createSaleViewModel.setCustomersForCreatingSaleList(customerModels);

        this.cache.put("createSaleModel", createSaleViewModel);
        return super.view("/views/sales/create", createSaleViewModel);
    }

    @PostMapping("/create/review")
    public ModelAndView createSaleReview(@Valid @ModelAttribute CreateSaleBindingModel createSaleBindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return super.view("views/sales/create", this.cache.get("createSaleModel"));
        }

        this.cache.put("dataForSaleService", createSaleBindingModel);
        CreateSaleViewModel cachedSaleModel = (CreateSaleViewModel) this.cache.get("createSaleModel");

        CarForCreatingSaleViewModel carViewModel = cachedSaleModel.getCarsForCreatingSaleList()
                .stream()
                .filter(car -> String.valueOf(car.getId()).equals(createSaleBindingModel.getCarId()))
                .findFirst()
                .orElse(null);

        CustomerForCreatingSaleViewModel customerViewModel = cachedSaleModel.getCustomersForCreatingSaleList()
                .stream()
                .filter(customer -> String.valueOf(customer.getId()).equals(createSaleBindingModel.getCustomerId()))
                .findFirst()
                .orElse(null);

        double carPrice = this.carService.findPrice(carViewModel.getId());
        carViewModel.setPrice(carPrice);
        CreateReviewViewModel createReviewViewModel = new CreateReviewViewModel();
        createReviewViewModel.setCar(carViewModel);
        createReviewViewModel.setCustomer(customerViewModel);

        if (createSaleBindingModel.getDiscount() != null) {
            createReviewViewModel.setDiscount(createSaleBindingModel.getDiscount());
        }

        return super.view("/views/sales/review", createReviewViewModel);
    }

    @PostMapping("/create")
    public ModelAndView finalizeSale() {
        CreateSaleBindingModel dataForCreatingSaleSerivces = (CreateSaleBindingModel) this.cache.get("dataForSaleService");
        SaleServiceModel saleServiceModel = new SaleServiceModel();

        CarServiceModel carServiceModel = this.carService
                .findById(Long.parseLong(dataForCreatingSaleSerivces.getCarId()));

        CustomerServiceModel customerServiceModel = this.customerService
                .findCustomerById(Long.parseLong(dataForCreatingSaleSerivces.getCustomerId()));

        saleServiceModel.setCar(carServiceModel);
        saleServiceModel.setCustomer(customerServiceModel);
        saleServiceModel.setDiscount(dataForCreatingSaleSerivces.getDiscount());
        this.saleService.create(saleServiceModel);
        return super.redirect("/sales");
    }

    @GetMapping("")
    public ModelAndView allSales() {
        List<SaleServiceModel> saleServiceModels = this.saleService.findAll();
        List<SaleViewModel> saleViewModels = new ArrayList<>();
        saleServiceModels.forEach(serviceModel -> {
            SaleViewModel saleViewModel = new SaleViewModel();
            saleViewModel.setId(serviceModel.getId());
            saleViewModel.setCar(serviceModel.getCar().getBrand());
            saleViewModel.setCustomer(serviceModel.getCustomer().getName());
            saleViewModel.setDiscount(serviceModel.getDiscount());
            saleViewModels.add(saleViewModel);
        });
        return super.view("/views/sales/all", saleViewModels);
    }

    @GetMapping("/{id}")
    public ModelAndView sale(@PathVariable long id) {
        SaleDetailsServiceModel serviceModel = this.saleService.findDetailsById(id);
        SaleDetailsViewModel viewModel = this.modelMapper.map(serviceModel, SaleDetailsViewModel.class);
        return super.view("/views/sales/details", viewModel);
    }
}