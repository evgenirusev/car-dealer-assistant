package com.cardealership.service;

import com.cardealership.domain.entity.Car;
import com.cardealership.domain.entity.Customer;
import com.cardealership.domain.entity.Sale;
import com.cardealership.domain.model.service.sales.SaleServiceModel;
import com.cardealership.repository.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<SaleServiceModel> findAll() {
        List<Sale> saleEntities = this.saleRepository.findAll();
        List<SaleServiceModel> saleModels = new ArrayList<>();
        saleEntities.forEach(sale -> {
            saleModels.add(this.modelMapper.map(sale, SaleServiceModel.class));
        });
        return saleModels;
    }

    @Override
    public List<SaleServiceModel> findSalesByCustomerId(Long id) {
        List<Sale> saleEntities = this.saleRepository.findSalesByCustomer_Id(id);
        List<SaleServiceModel> saleModels = new ArrayList<>();

        saleEntities.forEach(saleEntity -> {
            SaleServiceModel saleServiceModel = this.modelMapper.map(saleEntity, SaleServiceModel.class);
            saleModels.add(saleServiceModel);
        });

        return saleModels;
    }
}