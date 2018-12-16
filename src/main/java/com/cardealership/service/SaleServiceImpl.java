package com.cardealership.service;

import com.cardealership.domain.entity.Car;
import com.cardealership.domain.entity.Customer;
import com.cardealership.domain.entity.Sale;
import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.service.customers.CustomerServiceModel;
import com.cardealership.domain.model.service.sales.SaleDetailsServiceModel;
import com.cardealership.domain.model.service.sales.SaleServiceModel;
import com.cardealership.domain.model.view.cars.CarViewModel;
import com.cardealership.domain.model.view.customers.CustomerViewModel;
import com.cardealership.repository.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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

    @Override
    public SaleDetailsServiceModel findDetailsById(Long id) {
        SaleDetailsServiceModel saleDetailsServiceModel = new SaleDetailsServiceModel();

        Object salesByCustomerId = this.saleRepository.findSaleDetailsById(id);

        Object[] queryData = (Object[]) salesByCustomerId;

        String brand = (String) queryData[0];
        String model = (String) queryData[1];
        BigInteger travelledDistance = (BigInteger) queryData[2];
        String customer = (String) queryData[3];
        Double price = (Double) queryData[4];
        Double discount = (Double) queryData[5];

        CarServiceModel carServiceModel = new CarServiceModel();
        carServiceModel.setBrand(brand);
        carServiceModel.setModel(model);
        carServiceModel.setTravelledDistance(Long.parseLong(travelledDistance.toString()));

        CustomerServiceModel customerServiceModel = new CustomerServiceModel();
        customerServiceModel.setName(customer);

        saleDetailsServiceModel.setCar(carServiceModel);
        saleDetailsServiceModel.setCustomer(customerServiceModel);
        saleDetailsServiceModel.setDiscount(discount);
        saleDetailsServiceModel.setPrice(price);

        return saleDetailsServiceModel;
    }
}