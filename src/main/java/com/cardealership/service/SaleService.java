package com.cardealership.service;

import com.cardealership.domain.model.service.sales.SaleServiceModel;

import java.util.List;

public interface SaleService {
    void create(SaleServiceModel saleServiceModel);

    List<SaleServiceModel> findAll();
}
