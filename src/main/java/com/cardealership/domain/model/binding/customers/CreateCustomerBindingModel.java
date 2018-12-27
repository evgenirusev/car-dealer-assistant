package com.cardealership.domain.model.binding.customers;

import com.cardealership.constants.ValidationConstants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CreateCustomerBindingModel {
    @Size(min = 2, max = 60, message = ValidationConstants.CUSTOMER_NAME_LENGTH)
    private String name;

    @NotEmpty(message = ValidationConstants.CUSTOMER_BIRTH_DATE)
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