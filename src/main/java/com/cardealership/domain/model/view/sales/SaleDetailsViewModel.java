package com.cardealership.domain.model.view.sales;

import com.cardealership.domain.model.view.cars.CarViewModel;
import com.cardealership.domain.model.view.customers.CustomerViewModel;

public class SaleDetailsViewModel {
    private Double discount;

    private Double price;

    private CarViewModel car;

    private CustomerViewModel customer;

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public CarViewModel getCar() {
        return car;
    }

    public void setCar(CarViewModel car) {
        this.car = car;
    }

    public CustomerViewModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerViewModel customer) {
        this.customer = customer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}