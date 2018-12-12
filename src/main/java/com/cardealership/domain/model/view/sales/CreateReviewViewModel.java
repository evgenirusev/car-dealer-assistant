package com.cardealership.domain.model.view.sales;

import com.cardealership.domain.model.view.cars.CarForCreatingSaleViewModel;
import com.cardealership.domain.model.view.customers.CustomerForCreatingSaleModel;

public class CreateReviewViewModel {

    CarForCreatingSaleViewModel carForCreatingSaleViewModel;

    CustomerForCreatingSaleModel customerForCreatingSaleModel;

    private double discount;

    public CreateReviewViewModel() {
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public CarForCreatingSaleViewModel getCarForCreatingSaleViewModel() {
        return carForCreatingSaleViewModel;
    }

    public void setCarForCreatingSaleViewModel(CarForCreatingSaleViewModel carForCreatingSaleViewModel) {
        this.carForCreatingSaleViewModel = carForCreatingSaleViewModel;
    }

    public CustomerForCreatingSaleModel getCustomerForCreatingSaleModel() {
        return customerForCreatingSaleModel;
    }

    public void setCustomerForCreatingSaleModel(CustomerForCreatingSaleModel customerForCreatingSaleModel) {
        this.customerForCreatingSaleModel = customerForCreatingSaleModel;
    }
}