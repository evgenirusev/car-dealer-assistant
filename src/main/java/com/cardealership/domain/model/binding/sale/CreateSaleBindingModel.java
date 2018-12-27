package com.cardealership.domain.model.binding.sale;

import com.cardealership.constants.ValidationConstants;

import javax.validation.constraints.NotNull;

public class CreateSaleBindingModel {

    @NotNull(message = ValidationConstants.CUSTOMER_REQUIRED)
    private String customerId;

    @NotNull(message = ValidationConstants.CAR_REQUIRED)
    private String carId;

    private Double discount;

    public CreateSaleBindingModel() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}