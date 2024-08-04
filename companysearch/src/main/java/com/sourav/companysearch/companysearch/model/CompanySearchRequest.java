package com.sourav.companysearch.companysearch.model;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents a company search request.
 * 
 * @author Sourav Bhattacharya
 */
public class CompanySearchRequest {
    private String companyName;
    private String companyNumber;

    // At least one of companyName or companyNumber must be provided
    @NotBlank(message = "Either company name or company number must be provided")
    public String getSearchTerm() {
        return companyNumber != null ? companyNumber : companyName;
    }

    // Getters and setters
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }
}