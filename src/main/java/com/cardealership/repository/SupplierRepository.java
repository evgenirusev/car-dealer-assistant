package com.cardealership.repository;

import com.cardealership.domain.entity.SupplierServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierServiceModel, Long> {
    @Query(value = "SELECT *\n" +
            "  FROM suppliers AS s\n" +
            "WHERE s.is_importer = :isImporter", nativeQuery = true)
    List<SupplierServiceModel> findAllSuppliersByImporter(@Param("isImporter") boolean isImporter);

    SupplierServiceModel findSupplierById(Long id);
}