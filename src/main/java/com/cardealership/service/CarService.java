package com.cardealership.service;

import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.service.cars.CarWithPartsServiceModel;
import com.cardealership.domain.model.view.cars.CarForCreatingSaleModel;

import java.util.List;

public interface CarService {
    void createCar(CarServiceModel carServiceModel);

    void createCar(CarWithPartsServiceModel carServiceModel);

    List<CarForCreatingSaleModel> findAllCarModelsForCretingSale();
}