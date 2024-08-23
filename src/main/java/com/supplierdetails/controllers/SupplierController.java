package com.supplierdetails.controllers;

import com.supplierdetails.dtos.ErrorResponseDto;
import com.supplierdetails.entities.Supplier;
import com.supplierdetails.requests.CreateSupplierRequest;
import com.supplierdetails.requests.SupplierQueryRequest;
import com.supplierdetails.responses.SupplierResponse;
import com.supplierdetails.services.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Create and Query APIs for Suppliers", description = "REST APIs to create and query suppliers")
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/supplier", produces = {MediaType.APPLICATION_JSON_VALUE})
public class SupplierController {

    private SupplierService supplierService;

    @Operation(
            summary = "Create Supplier REST API",
            description = "REST API to create new suppliers"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody CreateSupplierRequest request) {
        Supplier createdSupplier = supplierService.createSupplier(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
    }

    @Operation(
            summary = "Fetch Supplier Details REST API",
            description = "REST API to Fetch Supplier details using different query parameter"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PostMapping("/query")
    public ResponseEntity<Page<SupplierResponse>> querySuppliers(@Valid @RequestBody SupplierQueryRequest request) {
        Page<SupplierResponse> suppliers = supplierService.querySuppliers(request);
        return ResponseEntity.ok(suppliers);
    }

}
