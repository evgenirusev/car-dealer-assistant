package com.cardealership.domain.model.service.customers;

public class CustomerServiceModel {

    private Long id;

    private String name;

    private String birthDate;

    private boolean isDriverYoung;

    public CustomerServiceModel() {
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isDriverYoung() {
        return isDriverYoung;
    }

    public void setDriverYoung(boolean driverYoung) {
        isDriverYoung = driverYoung;
    }
}