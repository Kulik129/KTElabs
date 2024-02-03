package com.example.producingwebservice.services.rest;

import com.example.producingwebservice.entity.Doctor;
import com.example.producingwebservice.entity.Patient;
import com.example.producingwebservice.entity.Ticket;
import com.example.producingwebservice.repository.DoctorRepository;
import com.example.producingwebservice.repository.PatientRepository;
import com.example.producingwebservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final TicketRepository ticketRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public void addPasientToTecket(Long ticketId, Long patientId, Long doctorId) { // переименовать метод

        System.out.println(ticketId + " " + " " + patientId + " " + doctorId);

        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);

        if (optionalTicket.isPresent() && optionalPatient.isPresent() && optionalDoctor.isPresent()) {
            Ticket ticket = optionalTicket.get();
            Patient patient = optionalPatient.get();
            Doctor doctor = optionalDoctor.get();

            ticket.setPatient(patient);
            ticket.setDoctor(doctor);
            ticketRepository.save(ticket);
        } else {
            // Обработка случая, когда ticketId или patientId не найдены
        }
    }

    public Patient createPatient(String fullNme, Date birthDate) {
        Patient createPatient = new Patient(fullNme, birthDate);
        patientRepository.save(createPatient);
        return createPatient;
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    public Optional<Patient> update(Long id, Date updatedBirthDate, String updatedFullName) {
        Optional<Patient> patient = patientRepository.findById(id);
        patient.get().setBirthDate(updatedBirthDate);
        patient.get().setFullName(updatedFullName);
        patientRepository.save(patient.get());
        return patient;
    }
}
