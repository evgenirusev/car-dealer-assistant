package com.cardealership.service;

import com.cardealership.domain.entity.Supplier;
import com.cardealership.domain.model.service.suppliers.SupplierServiceModel;
import com.cardealership.repository.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createSupplier(SupplierServiceModel supplierServiceModel) {
        Supplier supplierEntity = this.modelMapper.map(supplierServiceModel, Supplier.class);
        this.supplierRepository.save(supplierEntity);
    }
}
