package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.sale.CreateSaleBindingModel;
import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.service.customers.CustomerServiceModel;
import com.cardealership.domain.model.service.sales.SaleServiceModel;
import com.cardealership.domain.model.view.cars.CarForCreatingSaleViewModel;
import com.cardealership.domain.model.view.customers.CustomerForCreatingSaleModel;
import com.cardealership.domain.model.view.sales.CreateReviewViewModel;
import com.cardealership.domain.model.view.sales.CreateSaleViewModel;
import com.cardealership.domain.model.view.sales.SaleViewModel;
import com.cardealership.service.CarService;
import com.cardealership.service.CustomerService;
import com.cardealership.service.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

        List<CarForCreatingSaleViewModel> carModels = this.carService.findViewModelsForCreatingSale();
        createSaleViewModel.setCarsForCreatingSaleList(carModels);

        List<CustomerForCreatingSaleModel> customerModels = this.customerService.findAllForCreatingSale();
        createSaleViewModel.setCustomersForCreatingSaleList(customerModels);

        this.cache.put("createSaleModel", createSaleViewModel);
        return super.view("/views/sales/create", createSaleViewModel);
    }

    @PostMapping("/create/review")
    public ModelAndView createSaleReview(@ModelAttribute CreateSaleBindingModel createSaleBindingModel) {
        this.cache.put("dataForSaleService", createSaleBindingModel);
        CreateSaleViewModel cachedSaleModel = (CreateSaleViewModel) this.cache.get("createSaleModel");

        CarForCreatingSaleViewModel carViewModel = cachedSaleModel.getCarsForCreatingSaleList()
                .stream()
                .filter(car -> String.valueOf(car.getId()).equals(createSaleBindingModel.getCarId()))
                .findFirst()
                .orElse(null);

        CustomerForCreatingSaleModel customerViewModel = cachedSaleModel.getCustomersForCreatingSaleList()
                .stream()
                .filter(customer -> String.valueOf(customer.getId()).equals(createSaleBindingModel.getCustomerId()))
                .findFirst()
                .orElse(null);

        double carPrice = this.carService.findPrice(carViewModel.getId());
        carViewModel.setPrice(carPrice);

        CreateReviewViewModel createReviewViewModel = new CreateReviewViewModel();
        createReviewViewModel.setCar(carViewModel);
        createReviewViewModel.setCustomer(customerViewModel);
        createReviewViewModel.setDiscount(createSaleBindingModel.getDiscount());

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
        return super.redirect("/");
    }

    @GetMapping("")
    public ModelAndView allSales() {
        List<SaleServiceModel> saleServiceModels = this.saleService.findAll();
        List<SaleViewModel> saleViewModels = new ArrayList<>();
        saleServiceModels.forEach(serviceModel -> {
            SaleViewModel saleViewModel = new SaleViewModel();
            saleViewModel.setCar(serviceModel.getCar().getBrand());
            saleViewModel.setCustomer(serviceModel.getCustomer().getName());
            saleViewModel.setDiscount(serviceModel.getDiscount());
            saleViewModels.add(saleViewModel);
        });
        return super.view("/view/sales/all", saleViewModels);
    }
}