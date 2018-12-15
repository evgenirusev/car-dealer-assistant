package com.cardealership.domain.model.service.customers;

public class CustomerSalesServiceModel {

    private Double totalResources;

    private Long totalCars;

    public CustomerSalesServiceModel() {
    }

    public Double getTotalResources() {
        return totalResources;
    }

    public void setTotalResources(Double totalResources) {
        this.totalResources = totalResources;
    }

    public Long getTotalCars() {
        return totalCars;
    }

    public void setTotalCars(Long totalCars) {
        this.totalCars = totalCars;
    }
}