package com.cardealership.service;

import com.cardealership.domain.model.service.parts.PartServiceModel;
import com.cardealership.domain.model.view.cars.PartsForCreatingCarModel;
import com.cardealership.domain.model.view.parts.PartViewModel;

import java.util.List;

public interface PartService {
    void createPart(PartServiceModel partServiceModel);

    List<PartViewModel> findAllParts();

    List<PartsForCreatingCarModel> findAllPartsForCreatingCar();

    PartServiceModel findPartById(Long id);
}