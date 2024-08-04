package com.sourav.companysearch.companysearch.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a company search response.
 * 
 * @author Sourav Bhattacharya
 */
public class CompanySearchResponse {
    private int pageNumber; 
    private String kind;
    private int totalResults;
    private List<Company> items;

    public CompanySearchResponse() {
        // adding a no-argument constructor provides more flexibility.
        this.items = new ArrayList<>(); 
    }
    public CompanySearchResponse(int totalResults, List<Company> items) {
        this.totalResults = totalResults;
        this.items = items;
    }

// Getters and setters
    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public int getTotalResults() {
        return totalResults;
    }
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
    public List<Company> getItems() {
        return items;
    }
    public void setItems(List<Company> items) {
        this.items = items;
    }

    
   

}