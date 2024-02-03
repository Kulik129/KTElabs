package com.example.producingwebservice.api;

import com.example.producingwebservice.request.PatientRequest;
import com.example.producingwebservice.request.TicketRequest;
import com.example.producingwebservice.services.rest.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public void add(@RequestBody TicketRequest ticketRequest) {
        patientService.addPasientToTecket(ticketRequest.getTicketId(), ticketRequest.getPatientId(), ticketRequest.getDoctorId());
        System.out.println(ticketRequest.getTicketId());
        System.out.println(ticketRequest.getPatientId());
    }
    @PostMapping("/create")
    public ResponseEntity<PatientRequest> create(@RequestBody PatientRequest request) {
        patientService.createPatient(request.getFullName(), request.getBirthDate());
        return ResponseEntity.ok().body(request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatientRequest> update(@PathVariable Long id, @RequestBody PatientRequest request) {
        patientService.update(id, request.getBirthDate(),request.getFullName());
        System.out.println(request.getFullName());
        System.out.println(request.getBirthDate());
        return ResponseEntity.ok().body(request);
    }
}
