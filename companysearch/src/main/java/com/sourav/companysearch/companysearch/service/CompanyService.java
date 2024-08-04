package com.sourav.companysearch.companysearch.service;



import com.sourav.companysearch.companysearch.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for company search operations.
 * 
 * @author Sourav Bhattacharya
 */

@Service
public class CompanyService {

    private final WebClient webClient;
    private final String apiKey;

    @Autowired
    public CompanyService(WebClient webClient, @Value("${truproxyapi.api-key}") String apiKey) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    /**
     * Search for companies based on the provided request.
     * 
     * @param request The search request.
     * @param activeOnly Flag to filter only active companies.
     * @return CompanySearchResponse containing the search results.
     */
    public CompanySearchResponse searchCompanies(CompanySearchRequest request, boolean activeOnly) {
        String searchTerm = request.getCompanyNumber() != null ? request.getCompanyNumber() : request.getCompanyName();

        CompanySearchResponse searchResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/TruProxyAPI/rest/Companies/v1/Search")
                        .queryParam("Query", searchTerm)
                        .build())
                .header("x-api-key", apiKey)
                .retrieve()
                .bodyToMono(CompanySearchResponse.class)
                .block();

        if (searchResponse != null && searchResponse.getItems() != null) {
            List<Company> filteredCompanies = searchResponse.getItems().stream()
                    .filter(company -> !activeOnly || "active".equalsIgnoreCase(company.getCompanyStatus()))
                    .map(company -> {
                        List<Officer> officers = getCompanyOfficers(company.getCompanyNumber());
                        company.setOfficers(officers);
                        return company;
                    })
                    .collect(Collectors.toList());

            searchResponse.setItems(filteredCompanies);
            searchResponse.setTotalResults(filteredCompanies.size());
        }

        return searchResponse;
    }

    /**
     * Retrieve officers for a specific company.
     * 
     * @param companyNumber The company number.
     * @return List of active officers.
     */
    private List<Officer> getCompanyOfficers(String companyNumber) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/TruProxyAPI/rest/Companies/v1/Officers")
                        .queryParam("CompanyNumber", companyNumber)
                        .build())
                .header("x-api-key", apiKey)
                .retrieve()
                .bodyToMono(OfficerResponse.class)
                .map(OfficerResponse::getItems)
                .map(officers -> officers.stream()
                        .filter(officer -> officer.getResignedOn() == null)
                        .collect(Collectors.toList()))
                .block();
    }
}