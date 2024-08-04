package com.sourav.companysearch.companysearch.controller;

import com.sourav.companysearch.companysearch.model.Company;
import com.sourav.companysearch.companysearch.model.CompanySearchRequest;
import com.sourav.companysearch.companysearch.model.CompanySearchResponse;
import com.sourav.companysearch.companysearch.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    private CompanySearchResponse mockResponse;
    private CompanySearchResponse emptyResponse;

    @BeforeEach
    void setUp() {
        // Create a realistic mock response with some company data
        Company mockCompany = new Company();
        mockCompany.setCompanyNumber("06500244");
        mockCompany.setTitle("BBC LIMITED");
        mockCompany.setCompanyStatus("active"); // Set the company status
        // ... set other relevant fields in the mockCompany

        mockResponse = new CompanySearchResponse();
        mockResponse.setTotalResults(1);
        mockResponse.setItems(Collections.singletonList(mockCompany));

        emptyResponse = new CompanySearchResponse();
        emptyResponse.setTotalResults(0);
        emptyResponse.setItems(Collections.emptyList());
    }

    @Test
    void testSearchCompaniesByNumber() throws Exception {
        when(companyService.searchCompanies(any(CompanySearchRequest.class), anyBoolean()))
                .thenReturn(mockResponse);

        mockMvc.perform(post("/api/companies/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"companyNumber\":\"06500244\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalResults").value(1))
                .andExpect(jsonPath("$.items[0].companyNumber").value("06500244"))
                .andExpect(jsonPath("$.items[0].title").value("BBC LIMITED"));
    }

    @Test
    void testSearchCompaniesByName() throws Exception {
        when(companyService.searchCompanies(any(CompanySearchRequest.class), anyBoolean()))
                .thenReturn(mockResponse);

        mockMvc.perform(post("/api/companies/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"companyName\":\"BBC LIMITED\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalResults").value(1))
                .andExpect(jsonPath("$.items[0].companyNumber").value("06500244"))
                .andExpect(jsonPath("$.items[0].title").value("BBC LIMITED"));
    }

    @Test
    void testSearchActiveCompanies() throws Exception {
        when(companyService.searchCompanies(any(CompanySearchRequest.class), eq(true)))
                .thenReturn(mockResponse);

        mockMvc.perform(post("/api/companies/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"companyName\":\"BBC LIMITED\"}")
                        .param("activeOnly", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalResults").value(1))
                .andExpect(jsonPath("$.items[0].companyNumber").value("06500244"))
                .andExpect(jsonPath("$.items[0].title").value("BBC LIMITED"));
    }

    @Test
    void testNoCompaniesFound() throws Exception {
        when(companyService.searchCompanies(any(CompanySearchRequest.class), anyBoolean()))
                .thenReturn(emptyResponse);

        mockMvc.perform(post("/api/companies/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"companyName\":\"Nonexistent Company\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalResults").value(0))
                .andExpect(jsonPath("$.items").isEmpty()); 
    }
}
