package com.sourav.companysearch.application.service;

import com.sourav.companysearch.application.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final WebClient webClient;
    private final String apiKey;

    @Autowired
    public CompanyService(WebClient webClient, @Value("${truproxyapi.api-key}") String apiKey) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

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
                .onErrorResume(WebClientResponseException.class, ex -> {
                    System.err.println("Error searching companies: " + ex.getMessage());
                    return Mono.just(new CompanySearchResponse());
                })
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

    private List<Officer> getCompanyOfficers(String companyNumber) {
        try {
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
                    .onErrorResume(WebClientResponseException.class, ex -> {
                        System.err.println("Error fetching officers for company " + companyNumber + ": " + ex.getMessage());
                        return Mono.just(Collections.emptyList());
                    })
                    .block();
        } catch (Exception e) {
            System.err.println("Unexpected error fetching officers for company " + companyNumber + ": " + e.getMessage());
            return Collections.emptyList();
        }
    }
}