package com.cardealership.domain.model.view.parts;

import com.cardealership.domain.model.view.suppliers.SupplierViewModel;

public class PartViewModel {
    private Long id;

    private String name;

    private String price;

    private String quantity;

    private SupplierViewModel supplier;

    public PartViewModel() {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public SupplierViewModel getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierViewModel supplier) {
        this.supplier = supplier;
    }
}