package com.cardealership.service;

import com.cardealership.domain.entity.Supplier;
import com.cardealership.domain.model.service.suppliers.SupplierServiceModel;
import com.cardealership.domain.model.view.suppliers.SupplierForCreatingPartModel;
import com.cardealership.repository.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
    public void create(SupplierServiceModel supplierServiceModel) {
        Supplier supplierEntity = this.modelMapper.map(supplierServiceModel, Supplier.class);
        this.supplierRepository.save(supplierEntity);
    }

    @Override
    public Set<com.cardealership.domain.model.service.suppliers.SupplierServiceModel> findAllByImporter(boolean isImporter) {
        List<Supplier> supplierEntities = this.supplierRepository.findAllSuppliersByImporter(isImporter);
        Set<com.cardealership.domain.model.service.suppliers.SupplierServiceModel> supplierServiceSet = new LinkedHashSet<>();

        for (Supplier supplierEntity : supplierEntities) {
            com.cardealership.domain.model.service.suppliers.SupplierServiceModel supplierService = new com.cardealership.domain.model.service.suppliers.SupplierServiceModel();
            supplierService.setId(supplierEntity.getId());
            supplierService.setName(supplierEntity.getName());
            supplierServiceSet.add(supplierService);
        }
        return supplierServiceSet;
    }

    @Override
    public List<SupplierServiceModel> findAll() {
        List<SupplierServiceModel> supplierServiceModels = new ArrayList<>();
        this.supplierRepository.findAll().forEach(entity -> {
            SupplierServiceModel supplierServiceModel = this.modelMapper.map(entity, SupplierServiceModel.class);
            supplierServiceModels.add(supplierServiceModel);
        });
        return supplierServiceModels;
    }

    @Override
    public SupplierServiceModel findById(Long id) {
        Supplier supplier = this.supplierRepository.findSupplierById(id);
        return this.modelMapper.map(supplier, SupplierServiceModel.class);
    }

    @Override
    public void edit(SupplierServiceModel serviceModel) {
        Supplier supplier = this.supplierRepository.findById(serviceModel.getId()).orElse(null);
        this.modelMapper.map(serviceModel, supplier);
        this.supplierRepository.save(supplier);
    }

    @Override
    public void deleteById(Long id) {
        this.supplierRepository.deleteById(id);
    }
}