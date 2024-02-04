package com.example.producingwebservice.services.rest;

import com.example.producingwebservice.entity.Doctor;
import com.example.producingwebservice.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void deleteDoctorByID(Long id) {
        doctorRepository.deleteById(id);
    }

    public Optional<Doctor> updateDoctor(Long id, String updatedFullName) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        doctor.get().setFullName(updatedFullName);
        doctorRepository.save(doctor.get());
        return doctor;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
