package com.cardealership.domain.model.service.cars;

import java.util.List;

public class CarWithPartsServiceModel extends CarServiceModel {
    private List<String> parts;

    public CarWithPartsServiceModel() {
    }

    public List<String> getParts() {
        return parts;
    }

    public void setParts(List<String> parts) {
        this.parts = parts;
    }
}
