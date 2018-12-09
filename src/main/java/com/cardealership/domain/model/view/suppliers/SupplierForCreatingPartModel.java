package com.cardealership.domain.model.view.suppliers;

public class SupplierForCreatingPartModel {

    private Long supplierId;

    private String name;

    public SupplierForCreatingPartModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}