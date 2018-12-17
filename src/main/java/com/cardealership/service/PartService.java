package com.cardealership.service;

import com.cardealership.domain.model.service.parts.PartServiceModel;
import com.cardealership.domain.model.view.parts.PartsForCreatingCarModel;

import java.util.List;

public interface PartService {
    void craete(PartServiceModel partServiceModel);

    List<PartServiceModel> findAll();

    // TODO: decouple service layer from presentation (MUST RETURN SERVICE MODEL)
    List<PartsForCreatingCarModel> findAllForCreatingCar();

    PartServiceModel findById(Long id);

    void edit(PartServiceModel serviceModel);
}