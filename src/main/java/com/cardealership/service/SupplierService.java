package com.cardealership.service;

import com.cardealership.domain.model.service.suppliers.SupplierServiceModel;

import java.util.Set;

public interface SupplierService {
    void createSupplier(SupplierServiceModel supplierServiceModel);

    Set<SupplierServiceModel> findAllByImporter(boolean isImporter);
}
