package ru.ktelabs.producingwebservice.api.rest;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import ru.ktelabs.producingwebservice.entity.Doctor;
import ru.ktelabs.producingwebservice.exception.NotFoundException;
import ru.ktelabs.producingwebservice.exception.NullUserException;
import ru.ktelabs.producingwebservice.exception.UsersNotFoundException;
import ru.ktelabs.producingwebservice.request.DoctorRequest;
import ru.ktelabs.producingwebservice.request.TicketDoctorRequest;
import ru.ktelabs.producingwebservice.services.rest.DoctorService;
import ru.ktelabs.producingwebservice.services.rest.TicketService;
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
        try {
            doctorService.createDoctor(request.getFullName());
            return ResponseEntity.ok().body(request);
        } catch (NullUserException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/assign")
    @Operation(summary = "Занятие слота по его ID", description = "Занятие слота времени по его ID, слот будет занят врачем")
    public ResponseEntity<TicketDoctorRequest> assignTicketDoctor(@RequestBody TicketDoctorRequest ticketDoctorRequest) {
        try {
            ticketService.appointTicketDoctor(ticketDoctorRequest.getTicketId(), ticketDoctorRequest.getDoctorId());
            return ResponseEntity.ok().body(ticketDoctorRequest);
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    @Operation(summary = "Список докторов", description = "Получение списка врачей из базы данных")
    public ResponseEntity<List<Doctor>> getAll() {
        try {
            return ResponseEntity.ok().body(doctorService.getAllDoctors());
        } catch (UsersNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление доктора", description = "Удаление врача из базы данных по его ID")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        try {
            doctorService.deleteDoctorByID(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление ФИО доктора", description = "Обновление ФИО врача в базе данных по его ID")
    public ResponseEntity<DoctorRequest> updateDoctor(@RequestBody DoctorRequest request, @PathVariable Long id) {
        try {
            doctorService.updateDoctor(id, request.getFullName());
            return ResponseEntity.ok().body(request);
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
