package com.cardealership.domain.model.view.cars;

import com.cardealership.domain.model.view.parts.PartViewModel;

import java.util.Set;

public class CarWithPartsViewModel {
    private String brand;

    private String model;

    private long travelledDistance;

    private Set<PartViewModel> parts;

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

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<PartViewModel> getParts() {
        return parts;
    }

    public void setParts(Set<PartViewModel> parts) {
        this.parts = parts;
    }
}