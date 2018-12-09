package com.cardealership.service;

import com.cardealership.domain.entity.Supplier;
import com.cardealership.domain.model.service.suppliers.SupplierServiceModel;
import com.cardealership.domain.model.view.suppliers.SupplierForCreatingPartModel;

import java.util.Set;

public interface SupplierService {
    void createSupplier(SupplierServiceModel supplierServiceModel);

    Set<SupplierServiceModel> findAllByImporter(boolean isImporter);

    Set<SupplierForCreatingPartModel> findAllSuppliers();

    Supplier findSupplierById(Long id);
}