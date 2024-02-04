package ru.ktelabs.producingwebservice.api.rest;

import org.springframework.http.HttpStatus;
import ru.ktelabs.producingwebservice.exception.NotFoundException;
import ru.ktelabs.producingwebservice.request.TicketFreeRequest;
import ru.ktelabs.producingwebservice.services.rest.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    @Operation(summary = "Получение слотов времени к врчу по дате", description = "Получение всех слотов врмени к указанному врачу по его ID и на указанную дату")
    public ResponseEntity<List<Date>> getAllFreeTicketsByIdDoctor(@RequestBody TicketFreeRequest request) {
        try {
            ticketService.getTicketsByDate(request.getDoctorId(), request.getDate());
            return ResponseEntity.ok().body(ticketService.getTicketsByDate(request.getDoctorId(), request.getDate()));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение слотов времени", description = "Получение слотов врмени занятых одним пациентом по его ID")
    public ResponseEntity<List<Date>> getAllTicketsByPatientId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(ticketService.getAllTicketByPatientId(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
