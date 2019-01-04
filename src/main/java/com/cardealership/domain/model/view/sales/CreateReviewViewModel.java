package com.cardealership.domain.model.view.sales;

import com.cardealership.domain.model.view.cars.CarForCreatingSaleViewModel;
import com.cardealership.domain.model.view.customers.CustomerForCreatingSaleViewModel;

public class CreateReviewViewModel {

    CarForCreatingSaleViewModel car;

    CustomerForCreatingSaleViewModel customer;

    private double discount;

    public CreateReviewViewModel() {
    }

    public CarForCreatingSaleViewModel getCar() {
        return car;
    }

    public void setCar(CarForCreatingSaleViewModel car) {
        this.car = car;
    }

    public CustomerForCreatingSaleViewModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerForCreatingSaleViewModel customer) {
        this.customer = customer;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}