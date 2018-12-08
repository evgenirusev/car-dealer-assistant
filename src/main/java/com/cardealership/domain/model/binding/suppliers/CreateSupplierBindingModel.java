package com.cardealership.domain.model.binding.suppliers;

public class CreateSupplierBindingModel {
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
