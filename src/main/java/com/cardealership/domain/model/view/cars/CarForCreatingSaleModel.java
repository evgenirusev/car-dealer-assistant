package com.cardealership.domain.model.view.cars;

public class CarForCreatingSaleModel {

    private Long id;

    private String brand;

    private String model;

    public CarForCreatingSaleModel() {
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}