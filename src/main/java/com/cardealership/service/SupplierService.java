package com.cardealership.service;

import com.cardealership.domain.model.service.suppliers.SupplierServiceModel;
import com.cardealership.domain.model.view.suppliers.SupplierForCreatingPartModel;

import java.util.List;
import java.util.Set;

public interface SupplierService {
    void create(SupplierServiceModel supplier);

    Set<SupplierServiceModel> findAllByImporter(boolean isImporter);

    List<SupplierServiceModel> findAll();

    SupplierServiceModel findById(Long id);

    void edit(SupplierServiceModel serviceModel);

    void deleteById(Long id);
}