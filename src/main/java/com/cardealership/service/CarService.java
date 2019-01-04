package com.cardealership.service;

import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.view.cars.CarForCreatingSaleViewModel;

import java.util.List;

public interface CarService {
    void createCar(CarServiceModel carServiceModel);

    CarServiceModel findById(Long id);

    List<CarServiceModel> findAll();

    double findPrice(Long id);

    List<CarServiceModel> findAsc();

    List<CarServiceModel> findCarsByBrandOrderedByModelAscAndDistanceDesc(String brand);
}