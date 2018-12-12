package com.cardealership.domain.model.binding.sale;

public class CreateSaleBindingModel {
    private String customerId;

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