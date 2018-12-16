package com.cardealership.repository;

import com.cardealership.domain.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findSalesByCustomer_Id(Long customer_id);

    @Query(value = "SELECT c.brand, c.model, c.traveled_distance, cu.name, SUM(p.price), s.discount\n" +
            "FROM sales AS s\n" +
            "INNER JOIN cars AS c\n" +
            "ON c.id = s.car_id\n" +
            "INNER JOIN customers AS cu\n" +
            "ON cu.id = s.customer_id\n" +
            "INNER JOIN parts_cars AS p_c\n" +
            "ON p_c.car_id = c.id\n" +
            "INNER JOIN parts AS p\n" +
            "ON p_c.part_id = p.id\n" +
            "WHERE s.id = :id", nativeQuery = true)
    Object findSaleDetailsById(@Param("id") Long id);
}