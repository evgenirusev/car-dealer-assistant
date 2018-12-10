package com.cardealership.service;

import com.cardealership.domain.entity.SupplierServiceModel;
import com.cardealership.domain.model.view.suppliers.SupplierForCreatingPartModel;

import java.util.Set;

public interface SupplierService {
    void createSupplier(com.cardealership.domain.model.service.suppliers.SupplierServiceModel supplierServiceModel);

    Set<com.cardealership.domain.model.service.suppliers.SupplierServiceModel> findAllByImporter(boolean isImporter);

    Set<SupplierForCreatingPartModel> findAllSuppliers();

    SupplierServiceModel findSupplierById(Long id);
}