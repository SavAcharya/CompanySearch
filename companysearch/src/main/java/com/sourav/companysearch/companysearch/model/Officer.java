package com.sourav.companysearch.companysearch.model;

import java.util.Map;

/**
 * Represents a company officer.
 * 
 * @author Sourav Bhattacharya
 */
public class Officer {
   private Address address;
    private String name;
    private String appointedOn;
    private String resignedOn;
    private String officerRole;
    private Map<String, Map<String, String>> links;
    private Map<String, Integer> dateOfBirth;
    private String occupation;
    private String countryOfResidence;
    private String nationality;

     // Getters and setters 

    public Map<String, Map<String, String>> getLinks() {
        return links;
    }
    public void setLinks(Map<String, Map<String, String>> links) {
        this.links = links;
    }
    public Map<String, Integer> getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Map<String, Integer> dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getOccupation() {
        return occupation;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    public String getCountryOfResidence() {
        return countryOfResidence;
    }
    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
   
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOfficerRole() {
        return officerRole;
    }
    public void setOfficerRole(String officerRole) {
        this.officerRole = officerRole;
    }
    public String getAppointedOn() {
        return appointedOn;
    }
    public void setAppointedOn(String appointedOn) {
        this.appointedOn = appointedOn;
    }
    public String getResignedOn() {
        return resignedOn;
    }
    public void setResignedOn(String resignedOn) {
        this.resignedOn = resignedOn;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    
  
}