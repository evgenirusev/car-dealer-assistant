package com.cardealership.service;

import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.service.cars.CarWithPartsServiceModel;

public interface CarService {
    void createCar(CarServiceModel carServiceModel);

    void createCar(CarWithPartsServiceModel carServiceModel);
}
