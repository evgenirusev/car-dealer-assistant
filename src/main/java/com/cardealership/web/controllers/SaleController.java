package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.sale.CreateSaleBindingModel;
import com.cardealership.domain.model.view.cars.CarForCreatingSaleModel;
import com.cardealership.domain.model.view.customers.CustomerForCreatingSaleModel;
import com.cardealership.domain.model.view.sales.CarReviewViewModel;
import com.cardealership.domain.model.view.sales.CreateSaleViewModel;
import com.cardealership.domain.model.view.sales.CustomerReviewViewModel;
import com.cardealership.service.CarService;
import com.cardealership.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sales")
public class SaleController extends BaseController{

    private final CustomerService customerService;

    private final CarService carService;

    public SaleController(CustomerService customerService, CarService carService) {
        this.customerService = customerService;
        this.carService = carService;
    }

    @GetMapping("/create")
    public ModelAndView createSale(@ModelAttribute CreateSaleBindingModel createSaleBindingModel) {
        CreateSaleViewModel createSaleViewModel = new CreateSaleViewModel();
        createSaleViewModel.setCreateSaleBindingModel(createSaleBindingModel);

        List<CarForCreatingSaleModel> carModels = this.carService.findAllCarModelsForCretingSale();
        createSaleViewModel.setCarsForCreatingSaleList(carModels);

        List<CustomerForCreatingSaleModel> customerModels = this.customerService.findAllCustomersForCreatingSale();
        createSaleViewModel.setCustomersForCreatingSaleList(customerModels);

        return super.view("/views/sales/create", createSaleViewModel);
    }

    @PostMapping("/create/review")
    public ModelAndView createSaleReview(@ModelAttribute CreateSaleBindingModel createSaleBindingModel) {
        return super.view("/views/sales/create-review");
    }
}