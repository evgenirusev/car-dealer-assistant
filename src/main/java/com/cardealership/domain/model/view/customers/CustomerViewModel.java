package com.cardealership.domain.model.view.customers;

public class CustomerViewModel {
    private String name;

    private String birthDate;

    private boolean isDriverYoung;

    public CustomerViewModel() {
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