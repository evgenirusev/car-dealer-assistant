package com.cardealership.domain.model.binding.sale;

public class CreateSaleBindingModel {
    private String customer;

    private String car;

    private Double discount;

    public CreateSaleBindingModel() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}