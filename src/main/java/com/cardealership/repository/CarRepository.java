package com.cardealership.repository;

import com.cardealership.domain.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT SUM(p.price) FROM cars AS c\n" +
            "INNER JOIN parts_cars AS p_c\n" +
            "ON c.id = p_c.car_id\n" +
            "INNER JOIN parts AS p\n" +
            "ON p_c.part_id = p.id\n" +
            "WHERE c.id = :carId", nativeQuery = true)
    double getCarPrice(@Param("carId") Long carId);

    List<Car> findAllByOrderByBrandAsc();
}