package ru.ktelabs.producingwebservice.repository;

import ru.ktelabs.producingwebservice.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
