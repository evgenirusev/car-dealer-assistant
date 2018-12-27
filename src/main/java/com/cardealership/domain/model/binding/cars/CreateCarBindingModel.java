package com.cardealership.domain.model.binding.cars;

import com.cardealership.constants.ValidationConstants;

import javax.validation.constraints.Size;
import java.util.List;

public class CreateCarBindingModel {

    @Size(min = 2, max = 40, message = ValidationConstants.CAR_BRAND_LENGTH)
    private String brand;

    @Size(min = 2, max = 40, message = ValidationConstants.CAR_MODEL_LENGTH)
    private String model;

    private Long travelledDistance;

    @Size(min = 1, message = ValidationConstants.MINIMUM_AMOUNT_OF_CAR_PARTS)
    private List<Long> partIds;

    public CreateCarBindingModel() {
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

    public List<Long> getPartIds() {
        return partIds;
    }

    public void setPartIds(List<Long> partIds) {
        this.partIds = partIds;
    }
}