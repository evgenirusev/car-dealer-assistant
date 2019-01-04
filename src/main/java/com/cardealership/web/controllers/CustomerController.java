package com.cardealership.web.controllers;

import com.cardealership.domain.model.binding.customers.CreateCustomerBindingModel;
import com.cardealership.domain.model.binding.customers.EditCustomerBindingModel;
import com.cardealership.domain.model.service.customers.CustomerSalesServiceModel;
import com.cardealership.domain.model.service.customers.CustomerServiceModel;
import com.cardealership.domain.model.view.customers.CustomerDetailsViewModel;
import com.cardealership.domain.model.view.customers.CustomerOrderViewModel;
import com.cardealership.domain.model.view.customers.CustomerViewModel;
import com.cardealership.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public ModelAndView confirmCreateCustomer(@Valid @ModelAttribute CreateCustomerBindingModel createCustomerBindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return super.view("/views/customers/create");
        }

        CustomerServiceModel customerServiceModel = new CustomerServiceModel();
        customerServiceModel.setName(createCustomerBindingModel.getName());
        customerServiceModel.setBirthDate(LocalDate.parse(createCustomerBindingModel.getBirthDate()));
        this.customerService.createCustomer(customerServiceModel);
        return super.redirect("/customers/all/ascending");
    }

    @GetMapping("/all/ascending")
    public ModelAndView customersAscending() {
        CustomerOrderViewModel customerOrderViewModel = new CustomerOrderViewModel();
        customerOrderViewModel.setOrder("ascending");

        List<CustomerViewModel> customerViewModels = new ArrayList<>();
        List<CustomerServiceModel> customerServiceModels = this.customerService.findAllOrderByBirthDateAsc();
        if (customerServiceModels != null) {
            customerServiceModels.forEach(customerServiceModel -> {
                CustomerViewModel customerViewModel = this.modelMapper.map(customerServiceModel, CustomerViewModel.class);
                customerViewModels.add(customerViewModel);
            });
        }

        customerOrderViewModel.setCustomerViewModels(customerViewModels);
        return super.view("/views/customers/all", customerOrderViewModel);
    }

    @GetMapping("/all/descending")
    public ModelAndView customersDescending() {
        CustomerOrderViewModel customerOrderViewModel = new CustomerOrderViewModel();
        customerOrderViewModel.setOrder("descending");

        List<CustomerViewModel> customerViewModels = new ArrayList<>();
        List<CustomerServiceModel> customerServiceModels = this.customerService.findAllOrderByBirthDateDesc();
        if (customerServiceModels != null) {
            customerServiceModels.forEach(customerServiceModel -> {
                CustomerViewModel customerViewModel = this.modelMapper.map(customerServiceModel, CustomerViewModel.class);
                customerViewModels.add(customerViewModel);
            });
        }

        customerOrderViewModel.setCustomerViewModels(customerViewModels);
        return super.view("/views/customers/all", customerOrderViewModel);
    }

    @GetMapping("/{id}")
    public ModelAndView getSalesByCustomer(@PathVariable(name = "id") long id) {
        CustomerSalesServiceModel serviceModel = this.customerService.findCustomerSales(id);
        CustomerDetailsViewModel viewModel = this.modelMapper.map(serviceModel, CustomerDetailsViewModel.class);
        return super.view("/views/customers/sales", viewModel);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editConfirm(@ModelAttribute EditCustomerBindingModel customerBindingModel, @PathVariable("id") Long id) {
        CustomerServiceModel serviceModel = new CustomerServiceModel();
        serviceModel.setBirthDate(LocalDate.parse(customerBindingModel.getBirthDate()));
        serviceModel.setName(customerBindingModel.getName());
        this.customerService.editCustomer(serviceModel, id);
        return super.redirect("/customers/all/ascending");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCustomer(@PathVariable("id") Long id) {
        CustomerServiceModel serviceModel = this.customerService.findCustomerById(id);
        EditCustomerBindingModel bindingModel = this.modelMapper.map(serviceModel, EditCustomerBindingModel.class);
        return super.view("/views/customers/edit", bindingModel);
    }
}