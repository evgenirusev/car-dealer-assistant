package com.cardealership.domain.model.service.cars;

import com.cardealership.domain.model.service.parts.PartServiceModel;

import java.util.Set;

public class CarServiceModel {
    private Long id;

    private String brand;

    private String model;

    private Long travelledDistance;

    private Set<PartServiceModel> parts;

    public CarServiceModel() {
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

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<PartServiceModel> getParts() {
        return parts;
    }

    public void setParts(Set<PartServiceModel> parts) {
        this.parts = parts;
    }
}