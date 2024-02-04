package ru.ktelabs.producingwebservice.api.rest;

import org.springframework.http.HttpStatus;
import ru.ktelabs.producingwebservice.entity.Patient;
import ru.ktelabs.producingwebservice.exception.NotFoundException;
import ru.ktelabs.producingwebservice.exception.NullUserException;
import ru.ktelabs.producingwebservice.exception.UsersNotFoundException;
import ru.ktelabs.producingwebservice.request.PatientRequest;
import ru.ktelabs.producingwebservice.request.TicketPatientRequest;
import ru.ktelabs.producingwebservice.services.rest.PatientService;
import ru.ktelabs.producingwebservice.services.rest.TicketService;
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
    private final TicketService ticketService;

    @PostMapping
    @Operation(summary = "Занятие слота по его ID", description = "Занятие слота времени по его ID, слот будет занят пациентом")
    public ResponseEntity<TicketPatientRequest> assignTicket(@RequestBody TicketPatientRequest ticketRequest) {
        try {
            ticketService.appointTicket(ticketRequest.getTicketId(), ticketRequest.getPatientId());
            return ResponseEntity.ok().body(ticketRequest);
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    @Operation(summary = "Список пациентов", description = "Получение списка пациентов из базы данных")
    public ResponseEntity<List<Patient>> getAll() {
        try {
            return ResponseEntity.ok().body(patientService.getAllPatients());
        } catch (UsersNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    @Operation(summary = "Добавление пациента", description = "Создание нового пациента в базе данных")
    public ResponseEntity<PatientRequest> create(@RequestBody PatientRequest request) {
        try {
            patientService.createPatient(request.getFullName(), request.getBirthDate());
            return ResponseEntity.ok().body(request);
        } catch (NullUserException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пациента", description = "Удаление пациента из базы данных")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        try {
            patientService.deletePatientById(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление данных о пациенте", description = "Обновление данных о пациенте в базе данных")
    public ResponseEntity<PatientRequest> updatePatient(@PathVariable Long id, @RequestBody PatientRequest request) {
        try {
            patientService.updatePatient(id, request.getBirthDate(), request.getFullName());
            return ResponseEntity.ok().body(request);
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
