package com.example.producingwebservice.api;

import com.example.producingwebservice.repository.CountryRepository;
import com.example.producingwebservice.request.GetCountryRequest;
import com.example.producingwebservice.response.GetCountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;



@Endpoint
@Slf4j
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://example.com";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));
        log.info("request: {}", request.getName());
        log.info("request: {}", request.getId());
        return response;
    }
}