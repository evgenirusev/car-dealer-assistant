package com.cardealership.domain.model.binding.suppliers;

import com.cardealership.constants.ValidationConstants;

import javax.validation.constraints.Size;

public class CreateSupplierBindingModel {

    @Size(min=2, max = 60, message = ValidationConstants.SUPPLIER_NAME_LENGTH)
    private String name;

    private boolean importer;

    public CreateSupplierBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return importer;
    }

    public void setImporter(boolean importer) {
        this.importer = importer;
    }
}
