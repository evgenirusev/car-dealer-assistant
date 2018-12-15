package com.cardealership.repository;

import com.cardealership.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> getCustomersByOrderByBirthDateAsc();

    List<Customer> getCustomersByOrderByBirthDateDesc();

    @Query(value = "SELECT cu.name, SUM(p.price), COUNT(DISTINCT(s.id)), AVG(s.discount)\n" +
            "FROM customers AS cu\n" +
            "LEFT JOIN sales AS s\n" +
            "ON s.customer_id = cu.id\n" +
            "LEFT JOIN parts_cars AS p_c\n" +
            "ON s.car_id = p_c.car_id\n" +
            "LEFT JOIN parts AS p\n" +
            "ON p_c.part_id = p.id\n" +
            "WHERE cu.id = :id", nativeQuery = true)
    Object findSalesById(@Param("id") long id);
}