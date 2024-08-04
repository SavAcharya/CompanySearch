package com.sourav.companysearch.application.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sourav.companysearch.application.model.CompanySearchRequest;
import com.sourav.companysearch.application.model.CompanySearchResponse;
import com.sourav.companysearch.application.service.CompanyService;

/**
 * REST Controller for company search operations.
 * 
 * @author Sourav Bhattacharya
 */
@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * Endpoint for searching companies.
     * 
     * @param request The search request containing company name or number.
     * @param activeOnly Flag to filter only active companies.
     * @return ResponseEntity containing the search results.
     */
    @PostMapping("/search")
    public ResponseEntity<CompanySearchResponse> searchCompanies(
            @Valid @RequestBody CompanySearchRequest request,
            @RequestParam(defaultValue = "false") boolean activeOnly) {
        CompanySearchResponse response = companyService.searchCompanies(request, activeOnly);
        return ResponseEntity.ok(response);
    }
}