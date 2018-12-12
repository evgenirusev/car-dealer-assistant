package com.cardealership.domain.model.view.sales;

public class CustomerReviewViewModel {
    private String name;

    private boolean young;

    public CustomerReviewViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isYoung() {
        return young;
    }

    public void setYoung(boolean young) {
        this.young = young;
    }
}