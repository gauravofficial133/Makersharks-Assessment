package com.supplierdetails.responses;

import com.supplierdetails.enums.ManufacturingProcess;
import com.supplierdetails.enums.NatureOfBusiness;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class SupplierResponse {
    private Long supplierId;
    private String companyName;
    private String website;
    private String location;
    private NatureOfBusiness natureOfBusiness;
    private Set<ManufacturingProcess> manufacturingProcesses;
}
