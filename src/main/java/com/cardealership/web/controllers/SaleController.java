package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.sale.CreateSaleBindingModel;
import com.cardealership.domain.model.view.cars.CarForCreatingSaleViewModel;
import com.cardealership.domain.model.view.customers.CustomerForCreatingSaleModel;
import com.cardealership.domain.model.view.sales.CreateReviewViewModel;
import com.cardealership.domain.model.view.sales.CreateSaleViewModel;
import com.cardealership.service.CarService;
import com.cardealership.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sales")
public class SaleController extends BaseController{

    private final CustomerService customerService;

    private final CarService carService;

    private Map<String, Object> cache;

    public SaleController(CustomerService customerService, CarService carService) {
        this.customerService = customerService;
        this.carService = carService;
        this.cache = new HashMap<>();
    }

    @GetMapping("/create")
    public ModelAndView createSale(@ModelAttribute CreateSaleBindingModel createSaleBindingModel) {
        this.cache.clear();
        CreateSaleViewModel createSaleViewModel = new CreateSaleViewModel();
        createSaleViewModel.setCreateSaleBindingModel(createSaleBindingModel);

        List<CarForCreatingSaleViewModel> carModels = this.carService.findAllCarModelsForCretingSale();
        createSaleViewModel.setCarsForCreatingSaleList(carModels);

        List<CustomerForCreatingSaleModel> customerModels = this.customerService.findAllCustomersForCreatingSale();
        createSaleViewModel.setCustomersForCreatingSaleList(customerModels);

        this.cache.put("createSaleModel", createSaleViewModel);
        return super.view("/views/sales/create", createSaleViewModel);
    }

    @PostMapping("/create/review")
    public ModelAndView createSaleReview(@ModelAttribute CreateSaleBindingModel createSaleBindingModel) {
        CreateSaleViewModel cachedSaleModel = (CreateSaleViewModel) this.cache.get("createSaleModel");
        this.cache.put("dataForSaleService", createSaleBindingModel);

        CarForCreatingSaleViewModel carViewModel = cachedSaleModel.getCarsForCreatingSaleList()
                .stream()
                .filter(car -> String.valueOf(car.getId()).equals(createSaleBindingModel.getCarId()))
                .findFirst()
                .orElse(null);

        CustomerForCreatingSaleModel customerViewModel = cachedSaleModel.getCustomersForCreatingSaleList()
                .stream()
                .filter(customer -> String.valueOf(customer.getId()).equals(createSaleBindingModel.getCarId()))
                .findFirst()
                .orElse(null);

        double carPrice = this.carService.GetCarPrice(carViewModel.getId());
        carViewModel.setPrice(carPrice);

        CreateReviewViewModel createReviewViewModel = new CreateReviewViewModel();
        createReviewViewModel.setCarForCreatingSaleViewModel(carViewModel);
        createReviewViewModel.setCustomerForCreatingSaleModel(customerViewModel);
        createReviewViewModel.setDiscount(createSaleBindingModel.getDiscount());

        // TODO: View
        return null;
    }
}