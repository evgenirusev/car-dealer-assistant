package com.cardealership.repository;

import com.cardealership.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> getCustomersByOrderByBirthDateAsc();

    List<Customer> getCustomersByOrderByBirthDateDesc();
}
