package com.example.producingwebservice.api.rest;

import com.example.producingwebservice.entity.Patient;
import com.example.producingwebservice.request.PatientRequest;
import com.example.producingwebservice.request.TicketRequest;
import com.example.producingwebservice.services.rest.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    @Operation(summary = "Занятие слота по его ID", description = "Занятие слота времени по его ID, слот будет занят пациентом и врачем")
    public ResponseEntity<TicketRequest> assignTicket(@RequestBody TicketRequest ticketRequest) {
        patientService.appointTicket(ticketRequest.getTicketId(), ticketRequest.getPatientId(), ticketRequest.getDoctorId());
        return ResponseEntity.ok().body(ticketRequest);
    }

    @GetMapping
    @Operation(summary = "Список пациентов", description = "Получение списка пациентов из базы данных")
    public ResponseEntity<List<Patient>> getAll() {
        return ResponseEntity.ok().body(patientService.getAllPatients());
    }

    @PostMapping("/create")
    @Operation(summary = "Добавление пациента", description = "Создание нового пациента в базе данных")
    public ResponseEntity<PatientRequest> create(@RequestBody PatientRequest request) {
        patientService.createPatient(request.getFullName(), request.getBirthDate());
        return ResponseEntity.ok().body(request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пациента", description = "Удаление пациента из базы данных")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление данных о пациенте", description = "Обновление данных о пациенте в базе данных")
    public ResponseEntity<PatientRequest> updatePatient(@PathVariable Long id, @RequestBody PatientRequest request) {
        patientService.update(id, request.getBirthDate(), request.getFullName());
        System.out.println(request.getFullName());
        System.out.println(request.getBirthDate());
        return ResponseEntity.ok().body(request);
    }
}
