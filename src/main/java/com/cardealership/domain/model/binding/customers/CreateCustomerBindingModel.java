package com.cardealership.domain.model.binding.customers;

public class CreateCustomerBindingModel {
    private String name;

    private String birthDate;

    public CreateCustomerBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}