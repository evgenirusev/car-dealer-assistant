package com.cardealership.service;

import com.cardealership.domain.entity.Car;
import com.cardealership.domain.entity.Customer;
import com.cardealership.domain.entity.Sale;
import com.cardealership.domain.model.service.sales.SaleServiceModel;
import com.cardealership.repository.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(SaleServiceModel saleServiceModel) {
        Sale saleEntity =  this.modelMapper.map(saleServiceModel, Sale.class);
        this.saleRepository.save(saleEntity);
    }
}