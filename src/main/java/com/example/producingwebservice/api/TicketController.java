package com.example.producingwebservice.api;

import com.example.producingwebservice.request.Free;
import com.example.producingwebservice.services.rest.TicketServiceRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketServiceRest ticketServiceRest;

    @GetMapping  // rename!!!
    public ResponseEntity<List<Date>> fetFree(@RequestBody Free request) { // rename!!!
        ticketServiceRest.getDates(request.getDoctorId());
        return ResponseEntity.ok().body(ticketServiceRest.getTicketsByDate(request.getDoctorId(), request.getDate()));
    }

    @GetMapping("/patient")  // rename!!!
    public ResponseEntity<List<Date>> getAllTicketPatientId(@RequestBody Free request) {  // rename!!!
        return ResponseEntity.ok().body(ticketServiceRest.getAllTicketByPatientId(request.getPatientId()));
    }
}

