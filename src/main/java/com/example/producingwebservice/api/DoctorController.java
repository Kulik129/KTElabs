package com.example.producingwebservice.api;

import com.example.producingwebservice.request.DoctorRequest;
import com.example.producingwebservice.services.rest.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorRequest> create(@RequestBody DoctorRequest request) {
        doctorService.createDoctor(request.getFullName());
        return ResponseEntity.ok().body(request);
    }
}
