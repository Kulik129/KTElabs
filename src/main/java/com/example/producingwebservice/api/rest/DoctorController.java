package com.example.producingwebservice.api.rest;

import com.example.producingwebservice.entity.Doctor;
import com.example.producingwebservice.request.DoctorRequest;
import com.example.producingwebservice.request.TicketDoctorRequest;
import com.example.producingwebservice.services.rest.DoctorService;
import com.example.producingwebservice.services.rest.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final TicketService ticketService;


    @PostMapping
    @Operation(summary = "Добавление доктора", description = "Создание нового врача в базе данных")
    public ResponseEntity<DoctorRequest> create(@RequestBody DoctorRequest request) {
        doctorService.createDoctor(request.getFullName());
        return ResponseEntity.ok().body(request);
    }
    @PostMapping("/assign")
    @Operation(summary = "Занятие слота по его ID", description = "Занятие слота времени по его ID, слот будет занят врачем")
    public ResponseEntity<TicketDoctorRequest> assignTicketDoctor(@RequestBody TicketDoctorRequest ticketDoctorRequest) {
        ticketService.appointTicketDoctor(ticketDoctorRequest.getTicketId(), ticketDoctorRequest.getDoctorId());
        return ResponseEntity.ok().body(ticketDoctorRequest);
    }

    @GetMapping
    @Operation(summary = "Список докторов", description = "Получение списка врачей из базы данных")
    public ResponseEntity<List<Doctor>> getAll() {
        return ResponseEntity.ok().body(doctorService.getAllDoctors());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление доктора", description = "Удаление врача из базы данных по его ID")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctorByID(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление ФИО доктора", description = "Обновление ФИО врача в базе данных по его ID")
    public ResponseEntity<DoctorRequest> updateDoctor(@RequestBody DoctorRequest request, @PathVariable Long id) {
        doctorService.updateDoctor(id, request.getFullName());
        return ResponseEntity.ok().body(request);
    }
}
