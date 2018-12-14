package com.cardealership.service;

import com.cardealership.domain.entity.Car;
import com.cardealership.domain.entity.Part;
import com.cardealership.domain.model.service.cars.CarServiceModel;
import com.cardealership.domain.model.service.parts.PartServiceModel;
import com.cardealership.domain.model.view.cars.CarForCreatingSaleViewModel;
import com.cardealership.repository.CarRepository;
import com.cardealership.repository.PartRepository;
import org.hibernate.collection.internal.PersistentSet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final ModelMapper modelMapper;

    // Decouple Repository Layer From Service Layer
    private final PartRepository partRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartService partService, ModelMapper modelMapper, SupplierService supplierService, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.partRepository = partRepository;
    }

    @Override
    public CarServiceModel findCarByid(Long id) {
        Car carEntity = this.carRepository.findById(id).orElse(null);
        return this.modelMapper.map(carEntity, CarServiceModel.class);
    }

    @Override
    public void createCar(CarServiceModel carServiceModel) {
        Car car = this.modelMapper.map(carServiceModel, Car.class);

        Set<Part> partEntities = new HashSet<>();

        carServiceModel.getParts().forEach(part -> {
            Part partEntity = this.partRepository.findPartById(part.getId());
            partEntity.addCar(car);
            partEntities.add(partEntity);
        });

        car.setParts(partEntities);
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
    public double GetCarPrice(Long id) {
        return this.carRepository.getCarPrice(id);
    }
}