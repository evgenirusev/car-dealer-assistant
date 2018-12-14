package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.customers.CreateCustomerBindingModel;
import com.cardealership.domain.model.service.customers.CustomerServiceModel;
import com.cardealership.domain.model.view.customers.CustomerOrderViewModel;
import com.cardealership.domain.model.view.customers.CustomerViewModel;
import com.cardealership.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController extends BaseController {

    private final ModelMapper modelMapper;

    private final CustomerService customerService;

    public CustomerController(ModelMapper modelMapper, CustomerService customerService) {
        this.modelMapper = modelMapper;
        this.customerService = customerService;
    }

    @GetMapping("/create")
    public ModelAndView createCustomer(@ModelAttribute CreateCustomerBindingModel createCustomerBindingModel) {
        return super.view("/views/customers/create");
    }

    @PostMapping("/create")
    public ModelAndView confirmCreateCustomer(@ModelAttribute CreateCustomerBindingModel createCustomerBindingModel) {
        CustomerServiceModel customerServiceModel = new CustomerServiceModel();
        customerServiceModel.setName(createCustomerBindingModel.getName());
        customerServiceModel.setBirthDate(LocalDate.parse(createCustomerBindingModel.getBirthDate()));
        this.customerService.createCustomer(customerServiceModel);
        return super.redirect("/");
    }

    @GetMapping("/all/ascending")
    public ModelAndView customersAscending() {
        CustomerOrderViewModel customerOrderViewModel = new CustomerOrderViewModel();
        customerOrderViewModel.setOrder("ascending");
        List<CustomerViewModel> customerViewModels = this.customerService.getAllCustomersOrderByBirthDateAscendingOrder();
        customerOrderViewModel.setCustomerViewModels(customerViewModels);
        return super.view("/views/customers/all", customerOrderViewModel);
    }

    @GetMapping("/all/descending")
    public ModelAndView customersDescending() {
        CustomerOrderViewModel customerOrderViewModel = new CustomerOrderViewModel();
        customerOrderViewModel.setOrder("descending");
        List<CustomerViewModel> customerViewModels = this.customerService.getAllCustomersOrderByBirthDateDescendingOrder();
        customerOrderViewModel.setCustomerViewModels(customerViewModels);
        return super.view("/views/customers/all", customerOrderViewModel);
    }
}