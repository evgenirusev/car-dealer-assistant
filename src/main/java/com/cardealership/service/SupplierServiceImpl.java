package com.cardealership.service;

import com.cardealership.domain.entity.SupplierServiceModel;
import com.cardealership.domain.model.view.suppliers.SupplierForCreatingPartModel;
import com.cardealership.repository.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void createSupplier(com.cardealership.domain.model.service.suppliers.SupplierServiceModel supplierServiceModel) {
        SupplierServiceModel supplierEntity = this.modelMapper.map(supplierServiceModel, SupplierServiceModel.class);
        this.supplierRepository.save(supplierEntity);
    }

    @Override
    public Set<com.cardealership.domain.model.service.suppliers.SupplierServiceModel> findAllByImporter(boolean isImporter) {
        List<SupplierServiceModel> supplierEntities = this.supplierRepository.findAllSuppliersByImporter(isImporter);
        Set<com.cardealership.domain.model.service.suppliers.SupplierServiceModel> supplierServiceSet = new LinkedHashSet<>();

        for (SupplierServiceModel supplierEntity : supplierEntities) {
            com.cardealership.domain.model.service.suppliers.SupplierServiceModel supplierService = new com.cardealership.domain.model.service.suppliers.SupplierServiceModel();
            supplierService.setId(supplierEntity.getId());
            supplierService.setName(supplierEntity.getName());
            supplierServiceSet.add(supplierService);
        }
        return supplierServiceSet;
    }

    @Override
    public Set<SupplierForCreatingPartModel> findAllSuppliers() {
        Set<SupplierForCreatingPartModel> supplierForCreatingPartModels = new LinkedHashSet<>();
        this.supplierRepository.findAll().forEach(supplier -> {
            SupplierForCreatingPartModel supplierForCreatingPartModel = new SupplierForCreatingPartModel();
            supplierForCreatingPartModel.setName(supplier.getName());
            supplierForCreatingPartModel.setSupplierId(supplier.getId());
            supplierForCreatingPartModels.add(supplierForCreatingPartModel);
        });
        return supplierForCreatingPartModels;
    }

    @Override
    public SupplierServiceModel findSupplierById(Long id) {
        return this.supplierRepository.findSupplierById(id);
    }
}