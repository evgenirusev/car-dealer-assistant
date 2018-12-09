package com.cardealership.service;

import com.cardealership.domain.entity.Part;
import com.cardealership.domain.entity.Supplier;
import com.cardealership.domain.model.service.parts.PartServiceModel;
import com.cardealership.repository.PartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;

    private final SupplierService supplierService;

    private final ModelMapper modelMapper;

    public PartServiceImpl(PartRepository partRepository, SupplierService supplierService, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.supplierService = supplierService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createPart(PartServiceModel partServiceModel) {
        Supplier supplier = this.supplierService.findSupplierById(partServiceModel.getId());
        Part part = this.modelMapper.map(partServiceModel, Part.class);
        part.setId(supplier.getId());
        this.partRepository.save(part);
    }
}