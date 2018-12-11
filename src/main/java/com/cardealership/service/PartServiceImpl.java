package com.cardealership.service;

import com.cardealership.domain.entity.Part;
import com.cardealership.domain.entity.SupplierServiceModel;
import com.cardealership.domain.model.service.parts.PartServiceModel;
import com.cardealership.domain.model.view.parts.PartsForCreatingCarModel;
import com.cardealership.domain.model.view.parts.PartViewModel;
import com.cardealership.repository.PartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        SupplierServiceModel supplier = this.supplierService.findSupplierById(partServiceModel.getId());
        Part part = this.modelMapper.map(partServiceModel, Part.class);
        part.setId(supplier.getId());
        part.setQuantity(1L);
        this.partRepository.save(part);
    }

    @Override
    public List<PartViewModel> findAllParts() {
        List<PartViewModel> partViewModels = new ArrayList<>();
        this.partRepository.findAll().forEach(part -> {
            PartViewModel partViewModel = this.modelMapper.map(part, PartViewModel.class);
            partViewModels.add(partViewModel);
        });
        return partViewModels;
    }

    @Override
    public List<PartsForCreatingCarModel> findAllPartsForCreatingCar() {
        List<PartsForCreatingCarModel> partsForCreatingCarModels = new ArrayList<>();
        this.partRepository.findAll().forEach(part -> {
            PartsForCreatingCarModel supplierForCreatingPartModel = new PartsForCreatingCarModel();
            supplierForCreatingPartModel.setName(part.getName());
            supplierForCreatingPartModel.setPartId(part.getId());
            partsForCreatingCarModels.add(supplierForCreatingPartModel);
        });
        return partsForCreatingCarModels;
    }

    @Override
    public PartServiceModel findPartById(Long id) {
        Part part = this.partRepository.findPartById(id);
        PartServiceModel partServiceModel = this.modelMapper.map(part, PartServiceModel.class);
        return partServiceModel;
    }
}