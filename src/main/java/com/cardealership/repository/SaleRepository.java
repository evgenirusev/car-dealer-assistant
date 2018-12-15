package com.cardealership.repository;

import com.cardealership.domain.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findSalesByCustomer_Id(Long customer_id);
}
