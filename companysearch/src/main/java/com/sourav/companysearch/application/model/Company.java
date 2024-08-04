package com.sourav.companysearch.application.model;

import java.util.List;
import java.util.Map;

/**
 * Represents a company.
 * 
 * @author Sourav Bhattacharya
 */
public class Company {
    private String companyStatus;
    private String addressSnippet;
    private String dateOfCreation;
    private Map<String,List<Integer>> matches; 
    private String description;
    private Map<String, String> links;
    private String companyNumber;
    private String title;
    private String companyType;
    private Address address;
    private String kind;
    private List<String> descriptionIdentifier;
    private List<Officer> officers;

    // Getters and setters
    public String getCompanyStatus() {
        return companyStatus;
    }
    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }
    public String getAddressSnippet() {
        return addressSnippet;
    }
    public void setAddressSnippet(String addressSnippet) {
        this.addressSnippet = addressSnippet;
    }
    public String getDateOfCreation() {
        return dateOfCreation;
    }
    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
    public Map<String, List<Integer>> getMatches() {
        return matches;
    }
    public void setMatches(Map<String, List<Integer>> matches) {
        this.matches = matches;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Map<String, String> getLinks() {
        return links;
    }
    public void setLinks(Map<String, String> links) {
        this.links = links;
    }
    public String getCompanyNumber() {
        return companyNumber;
    }
    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCompanyType() {
        return companyType;
    }
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public List<String> getDescriptionIdentifier() {
        return descriptionIdentifier;
    }
    public void setDescriptionIdentifier(List<String> descriptionIdentifier) {
        this.descriptionIdentifier = descriptionIdentifier;
    }
    public List<Officer> getOfficers() {
        return officers;
    }
    public void setOfficers(List<Officer> officers) {
        this.officers = officers;
    }

    


   

    
}