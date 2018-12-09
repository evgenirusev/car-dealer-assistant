package com.cardealership.domain.model.view.customers;

import java.util.List;

public class CustomerOrderViewModel {
    List<CustomerViewModel> customerViewModels;

    private String order;

    public CustomerOrderViewModel() {
    }

    public List<CustomerViewModel> getCustomerViewModels() {
        return customerViewModels;
    }

    public void setCustomerViewModels(List<CustomerViewModel> customerViewModels) {
        this.customerViewModels = customerViewModels;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
