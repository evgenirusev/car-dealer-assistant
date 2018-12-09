package com.cardealership.service;

import com.cardealership.domain.entity.Car;
import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void createCar(CarServiceModel carServiceModel) {
        Car carEntity = this.modelMapper.map(carServiceModel, Car.class);
        this.carRepository.save(carEntity);
    }
}