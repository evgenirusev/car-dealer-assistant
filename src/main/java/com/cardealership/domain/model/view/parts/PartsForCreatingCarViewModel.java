package com.cardealership.domain.model.view.parts;

public class PartsForCreatingCarViewModel {

    private Long partId;

    private String name;

    public PartsForCreatingCarViewModel() {
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}