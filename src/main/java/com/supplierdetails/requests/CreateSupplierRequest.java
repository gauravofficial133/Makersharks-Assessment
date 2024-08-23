package com.supplierdetails.requests;

import com.supplierdetails.enums.ManufacturingProcess;
import com.supplierdetails.enums.NatureOfBusiness;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

@Getter
@Setter
public class CreateSupplierRequest {
    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Website is required")
    @URL(message = "Website must be a valid URL")
    private String website;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Nature of business is required")
    private NatureOfBusiness natureOfBusiness;

    @NotEmpty(message = "At least one manufacturing process is required")
    private Set<ManufacturingProcess> manufacturingProcesses;

}
