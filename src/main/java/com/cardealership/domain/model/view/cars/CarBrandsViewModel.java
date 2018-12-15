package com.cardealership.domain.model.view.cars;

public class CarBrandsViewModel {
    private Long id;

    private String brand;

    public CarBrandsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}