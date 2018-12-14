package com.cardealership.domain.model.view.sales;

import com.cardealership.domain.model.view.cars.CarForCreatingSaleViewModel;
import com.cardealership.domain.model.view.customers.CustomerForCreatingSaleModel;

public class CreateReviewViewModel {

    CarForCreatingSaleViewModel car;

    CustomerForCreatingSaleModel customer;

    private double discount;

    public CreateReviewViewModel() {
    }

    public CarForCreatingSaleViewModel getCar() {
        return car;
    }

    public void setCar(CarForCreatingSaleViewModel car) {
        this.car = car;
    }

    public CustomerForCreatingSaleModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerForCreatingSaleModel customer) {
        this.customer = customer;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}