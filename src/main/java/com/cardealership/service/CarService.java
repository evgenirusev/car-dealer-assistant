package com.cardealership.service;

import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.view.cars.CarBrandsViewModel;
import com.cardealership.domain.model.view.cars.CarForCreatingSaleViewModel;

import java.util.List;

public interface CarService {
    void createCar(CarServiceModel carServiceModel);

    CarServiceModel findById(Long id);

    // TODO: decouple service layer from presentation layer (MUST RETURN SERVICE MODEL)
    List<CarForCreatingSaleViewModel> findViewModelsForCreatingSale();

    double findPrice(Long id);

    List<CarServiceModel> findAsc();
}