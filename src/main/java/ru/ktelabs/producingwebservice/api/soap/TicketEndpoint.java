package ru.ktelabs.producingwebservice.api.soap;

import ru.ktelabs.producingwebservice.request.soap.GetTicketRequest;
import ru.ktelabs.producingwebservice.services.soap.TicketServiceS;
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
    private static final String NAMESPACE_URI = "https://ktelabs.ru";
    private final TicketServiceS ticketServiceS;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "setTicketRequest")
    @ResponsePayload
    public void createTickets(@RequestPayload GetTicketRequest request) {
        ticketServiceS.createSchedule(request.getStartTime(), request.getQuantity(),request.getDuration());
        log.info("Вызов метода для создания тикетов");
    }
}