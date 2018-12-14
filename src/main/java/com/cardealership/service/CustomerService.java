package com.cardealership.service;

import com.cardealership.domain.model.service.customers.CustomerServiceModel;
import com.cardealership.domain.model.view.customers.CustomerForCreatingSaleModel;
import com.cardealership.domain.model.view.customers.CustomerViewModel;

import java.util.List;

public interface CustomerService {
    void createCustomer(CustomerServiceModel customerServiceModel);

    List<CustomerViewModel> getAllCustomersOrderByBirthDateAscendingOrder();

    List<CustomerViewModel> getAllCustomersOrderByBirthDateDescendingOrder();

    List<CustomerForCreatingSaleModel> findAllCustomersForCreatingSale();

    CustomerServiceModel findCustomerById(Long id);
}