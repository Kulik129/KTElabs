package com.example.producingwebservice.api;

import com.example.producingwebservice.request.GetTicketRequest;
import com.example.producingwebservice.services.soap.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
@Slf4j
@RequiredArgsConstructor
public class TicketEndpoint {
    private static final String NAMESPACE_URI = "http://example.com";
    private final TicketService ticketService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTicketRequest")
    @ResponsePayload
    public void getCountry(@RequestPayload GetTicketRequest request) {
        ticketService.createSchedule(request.getStartTime(), request.getQuantity(),request.getDuration());
    }
}