package ru.ktelabs.producingwebservice.repository;

import ru.ktelabs.producingwebservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
