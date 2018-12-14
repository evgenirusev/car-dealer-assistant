package com.cardealership.service;

import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.view.cars.CarForCreatingSaleViewModel;

import java.util.List;

public interface CarService {
    CarServiceModel findCarByid(Long id);

    void createCar(CarServiceModel carServiceModel);

    List<CarForCreatingSaleViewModel> findAllCarModelsForCretingSale();

    double GetCarPrice(Long id);
}