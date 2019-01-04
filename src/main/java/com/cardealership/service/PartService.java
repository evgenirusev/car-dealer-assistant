package com.cardealership.service;

import com.cardealership.domain.model.service.parts.PartServiceModel;

import java.util.List;

public interface PartService {
    void craete(PartServiceModel partServiceModel);

    List<PartServiceModel> findAll();

    PartServiceModel findById(Long id);

    void edit(PartServiceModel serviceModel);
}