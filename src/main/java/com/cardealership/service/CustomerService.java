package com.cardealership.service;

import com.cardealership.domain.model.service.customers.CustomerServiceModel;
import com.cardealership.domain.model.service.customers.CustomerSalesServiceModel;
import com.cardealership.domain.model.view.customers.CustomerForCreatingSaleModel;
import com.cardealership.domain.model.view.customers.CustomerViewModel;

import java.util.List;

public interface CustomerService {
    void createCustomer(CustomerServiceModel customerServiceModel);

    List<CustomerViewModel> findAllOrderByBirthDateAsc();

    List<CustomerViewModel> findAllOrderByBirthDateDesc();

    // TODO: decouple service layer from presentation layer (MUST RETURN SERVICE MODEL)
    List<CustomerForCreatingSaleModel> findAllForCreatingSale();

    CustomerServiceModel findCustomerById(Long id);

    CustomerSalesServiceModel findCustomerSales(Long id);

    void editCustomer(CustomerServiceModel customerServiceModel, Long id);
}