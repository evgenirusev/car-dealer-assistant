package com.cardealership.domain.model.service.parts;

import com.cardealership.domain.entity.Supplier;
import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.service.suppliers.SupplierServiceModel;

import java.util.Set;

public class PartServiceModel {
    private Long id;

    private String name;

    private Double price;

    private SupplierServiceModel supplier;

    private Set<CarServiceModel> cars;

    public PartServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public SupplierServiceModel getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierServiceModel supplier) {
        this.supplier = supplier;
    }

    public Set<CarServiceModel> getCars() {
        return cars;
    }

    public void setCars(Set<CarServiceModel> cars) {
        this.cars = cars;
    }

    public void addCar(CarServiceModel carServiceModel) {
        this.cars.add(carServiceModel);
    }
}