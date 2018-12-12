package com.cardealership.service;

import com.cardealership.domain.entity.Sale;
import com.cardealership.domain.model.service.sales.SaleServiceModel;
import com.cardealership.repository.SaleRepository;
import org.modelmapper.ModelMapper;

public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createSale(SaleServiceModel saleServiceModel) {
        Sale saleEntity = this.modelMapper.map(saleServiceModel, Sale.class);
        this.saleRepository.save(saleEntity);
    }
}