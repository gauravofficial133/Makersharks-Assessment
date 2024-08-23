package com.supplierdetails.services;

import com.supplierdetails.entities.Supplier;
import com.supplierdetails.repositories.SupplierRepository;
import com.supplierdetails.requests.CreateSupplierRequest;
import com.supplierdetails.requests.SupplierQueryRequest;
import com.supplierdetails.responses.SupplierResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierService {

    private SupplierRepository supplierRepository;

    public Supplier createSupplier(CreateSupplierRequest createSupplierRequest) {
        Supplier supplier = new Supplier();
        supplier.setCompanyName(createSupplierRequest.getCompanyName());
        supplier.setWebsite(createSupplierRequest.getWebsite());
        supplier.setLocation(createSupplierRequest.getLocation());
        supplier.setNatureOfBusiness(createSupplierRequest.getNatureOfBusiness());
        supplier.setManufacturingProcesses(createSupplierRequest.getManufacturingProcesses());
        return supplierRepository.save(supplier);
    }

    public Page<SupplierResponse> querySuppliers(SupplierQueryRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Page<Supplier> supplierPage = supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcessesContaining(
                request.getLocation(),
                request.getNatureOfBusiness(),
                request.getProcess(),
                pageable
        );

        return supplierPage.map(this::mapToSupplierResponse);
    }

    private SupplierResponse mapToSupplierResponse(Supplier supplier) {
        return new SupplierResponse(
                supplier.getSupplierId(),
                supplier.getCompanyName(),
                supplier.getWebsite(),
                supplier.getLocation(),
                supplier.getNatureOfBusiness(),
                supplier.getManufacturingProcesses()
        );
    }
}
