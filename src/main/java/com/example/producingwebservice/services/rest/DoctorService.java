package com.example.producingwebservice.services.rest;

import com.example.producingwebservice.entity.Doctor;
import com.example.producingwebservice.entity.Patient;
import com.example.producingwebservice.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public Doctor createDoctor(String fullNme) {
        Doctor doctor = new Doctor(fullNme);
        doctorRepository.save(doctor);
        return doctor;
    }

    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }

    public Optional<Doctor> update(Long id,String updatedFullName) {
        Optional<Doctor> patient = doctorRepository.findById(id);
        patient.get().setFullName(updatedFullName);
        doctorRepository.save(patient.get());
        return patient;
    }
}
