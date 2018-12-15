package com.cardealership.service;

import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.view.cars.CarBrandsViewModel;
import com.cardealership.domain.model.view.cars.CarForCreatingSaleViewModel;

import java.util.List;

public interface CarService {
    void createCar(CarServiceModel carServiceModel);

    CarServiceModel findCarByid(Long id);

    List<CarForCreatingSaleViewModel> findAllCarModelsForCretingSale();

    double findCarPrice(Long id);

    List<CarBrandsViewModel> findAllCarBrands();

    List<CarServiceModel> findCarsAsc();
}