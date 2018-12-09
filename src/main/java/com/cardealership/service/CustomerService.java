package com.cardealership.service;

import com.cardealership.domain.model.service.customers.CustomerServiceModel;
import com.cardealership.domain.model.view.customers.CustomerViewModel;

import java.util.List;
import java.util.Set;

public interface CustomerService {
    void createCustomer(CustomerServiceModel customerServiceModel);

    List<CustomerViewModel> getAllCustomersOrderByBirthDateAscendingOrder();

    List<CustomerViewModel> getAllCustomersOrderByBirthDateDescendingOrder();
}