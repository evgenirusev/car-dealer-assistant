package com.cardealership.service;

import com.cardealership.domain.model.service.customers.CustomerServiceModel;
import com.cardealership.domain.model.service.customers.CustomerSalesServiceModel;
import com.cardealership.domain.model.view.customers.CustomerViewModel;

import java.util.List;

public interface CustomerService {
    void createCustomer(CustomerServiceModel customerServiceModel);

    List<CustomerServiceModel> findAllOrderByBirthDateAsc();

    List<CustomerServiceModel> findAllOrderByBirthDateDesc();

    CustomerServiceModel findCustomerById(Long id);

    CustomerSalesServiceModel findCustomerSales(Long id);

    void editCustomer(CustomerServiceModel customerServiceModel, Long id);
}