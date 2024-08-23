package com.supplierdetails.repositories;

import com.supplierdetails.entities.Supplier;
import com.supplierdetails.enums.ManufacturingProcess;
import com.supplierdetails.enums.NatureOfBusiness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Page<Supplier> findByLocationAndNatureOfBusinessAndManufacturingProcessesContaining(
            String location,
            NatureOfBusiness natureOfBusiness,
            ManufacturingProcess process,
            Pageable pageable);
}
