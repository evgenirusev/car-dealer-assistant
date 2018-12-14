package com.cardealership.service;

import com.cardealership.domain.entity.Car;
import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.view.cars.CarBrandsViewModel;
import com.cardealership.domain.model.view.cars.CarForCreatingSaleViewModel;
import com.cardealership.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartService partService, ModelMapper modelMapper, SupplierService supplierService) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CarServiceModel findCarByid(Long id) {
        Car carEntity = this.carRepository.findById(id).orElse(null);
        return this.modelMapper.map(carEntity, CarServiceModel.class);
    }

    @Override
    public void createCar(CarServiceModel carServiceModel) {
        ModelMapper modelMapper = new ModelMapper();
        Car car = modelMapper.map(carServiceModel, Car.class);
        this.carRepository.save(car);
    }

    @Override
    public List<CarForCreatingSaleViewModel> findAllCarModelsForCretingSale() {
        List<Car> carEntities = this.carRepository.findAll();

        List<CarForCreatingSaleViewModel> carModels = new ArrayList<>();

        carEntities.forEach(carEntity -> {
            CarForCreatingSaleViewModel carModel = this.modelMapper.map(carEntity, CarForCreatingSaleViewModel.class);
            carModels.add(carModel);
        });

        return carModels;
    }

    @Override
    public double findCarPrice(Long id) {
        return this.carRepository.getCarPrice(id);
    }

    @Override
    public List<CarBrandsViewModel> findAllCarBrands() {
        List<CarBrandsViewModel> carBrandsViewModels = new ArrayList<>();
        return null;
    }
}