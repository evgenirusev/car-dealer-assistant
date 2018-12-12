package com.cardealership.domain.model.service.sales;

import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.service.customers.CustomerServiceModel;

public class SaleServiceModel {
    private CarServiceModel carServiceModel;

    private CustomerServiceModel customerServiceModel;

    private Double discount;

    public SaleServiceModel() {
    }

    public CarServiceModel getCarServiceModel() {
        return carServiceModel;
    }

    public void setCarServiceModel(CarServiceModel carServiceModel) {
        this.carServiceModel = carServiceModel;
    }

    public CustomerServiceModel getCustomerServiceModel() {
        return customerServiceModel;
    }

    public void setCustomerServiceModel(CustomerServiceModel customerServiceModel) {
        this.customerServiceModel = customerServiceModel;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}