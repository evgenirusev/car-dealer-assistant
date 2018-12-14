package com.cardealership.service;

import com.cardealership.domain.entity.Car;
import com.cardealership.domain.entity.Customer;
import com.cardealership.domain.entity.Sale;
import com.cardealership.domain.model.service.sales.SaleServiceModel;
import com.cardealership.repository.CustomerRepository;
import com.cardealership.repository.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    private final ModelMapper modelMapper;

    private final CarService carService;

    private final PartService partService;

    // TODO: Decouple Service Layer from Repository Layer
    private final CustomerRepository customerRepository;

    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper, CarService carService, PartService partService, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.carService = carService;
        this.partService = partService;
        this.customerRepository = customerRepository;
    }

    @Override
    public void createSale(SaleServiceModel saleServiceModel) {

        Sale saleEntity =  new Sale();
        Car car = this.modelMapper.map(saleServiceModel.getCarServiceModel(), Car.class);
        Customer customerEntity = this.customerRepository.findById(1L).orElse(null);

        saleEntity.setCar(car);
        saleEntity.setCustomer(customerEntity);
        saleEntity.setDiscount(saleServiceModel.getDiscount());

        this.saleRepository.save(saleEntity);
    }
}