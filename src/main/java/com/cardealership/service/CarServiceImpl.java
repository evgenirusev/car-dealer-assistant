package com.cardealership.service;

import com.cardealership.domain.entity.Car;
import com.cardealership.domain.entity.Part;
import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.service.cars.CarWithPartsServiceModel;
import com.cardealership.domain.model.view.cars.CarForCreatingSaleModel;
import com.cardealership.repository.CarRepository;
import com.cardealership.repository.PartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final PartService partService;

    private final ModelMapper modelMapper;

    private final SupplierService supplierService;

    // TODO: detach repository layer from service layer
    private final PartRepository partRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartService partService, ModelMapper modelMapper, SupplierService supplierService, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.partService = partService;
        this.modelMapper = modelMapper;
        this.supplierService = supplierService;
        this.partRepository = partRepository;
    }

    @Override
    public void createCar(CarServiceModel carServiceModel) {
        Car carEntity = this.modelMapper.map(carServiceModel, Car.class);
        this.carRepository.save(carEntity);
    }

    @Override
    public void createCar(CarWithPartsServiceModel carServiceModel) {
        Car car = this.modelMapper.map(carServiceModel, Car.class);
        Set<Part> parts = new LinkedHashSet<>();
        carServiceModel.getParts().forEach(partId -> {
            Part part = this.partRepository.findPartById(Long.parseLong(partId));
            part.addCar(car);
            parts.add(part);
        });
        car.setParts(parts);
        this.carRepository.save(car);
    }

    @Override
    public List<CarForCreatingSaleModel> findAllCarModelsForCretingSale() {
        List<Car> carEntities = this.carRepository.findAll();
        List<CarForCreatingSaleModel> carModels =  new ArrayList<>();

        carEntities.forEach(carEntity -> {
            CarForCreatingSaleModel carModel = this.modelMapper.map(carEntity, CarForCreatingSaleModel.class);
            carModels.add(carModel);
        });

        return carModels;
    }
}