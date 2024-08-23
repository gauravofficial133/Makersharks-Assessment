package com.supplierdetails.requests;

import com.supplierdetails.enums.ManufacturingProcess;
import com.supplierdetails.enums.NatureOfBusiness;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierQueryRequest {
    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Nature of business is required")
    private NatureOfBusiness natureOfBusiness;

    @NotNull(message = "Manufacturing process is required")
    private ManufacturingProcess process;

    @Min(value = 0, message = "Page number should not be less than 0")
    private int pageNumber = 0;

    @Min(value = 1, message = "Page size should be at least 1")
    private int pageSize = 10;
}
