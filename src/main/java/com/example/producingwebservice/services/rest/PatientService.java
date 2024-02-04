package com.example.producingwebservice.services.rest;

import com.example.producingwebservice.entity.Doctor;
import com.example.producingwebservice.entity.Patient;
import com.example.producingwebservice.entity.Ticket;
import com.example.producingwebservice.exception.NotFoundException;
import com.example.producingwebservice.exception.NullUserException;
import com.example.producingwebservice.exception.UsersNotFoundException;
import com.example.producingwebservice.repository.DoctorRepository;
import com.example.producingwebservice.repository.PatientRepository;
import com.example.producingwebservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {
    private final TicketRepository ticketRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

//    public void appointTicket(Long ticketId, Long patientId, Long doctorId) {
//        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
//        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
//        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
//
//        if (optionalTicket.isPresent() && optionalPatient.isPresent() && optionalDoctor.isPresent()) {
//            Ticket ticket = optionalTicket.get();
//            Patient patient = optionalPatient.get();
//            Doctor doctor = optionalDoctor.get();
//
//            ticket.setPatient(patient);
//            ticket.setDoctor(doctor);
//            ticketRepository.save(ticket);
//            log.info("Успешное обновление информации!");
//        } else {
//            if (optionalTicket.isEmpty()) {
//                log.error("Ошибка обновления! Не найден талон с id {}", ticketId);
//                throw new NotFoundException("Билет с ID " + ticketId + " не найден!", "TNFID-0089");
//            }
//            if (optionalPatient.isEmpty()) {
//                log.error("Ошибка обновления! Не найден пациент с id {}", patientId);
//                throw new NotFoundException("Пациент с ID " + patientId + " не найден!", "PNFID-0088");
//            }
//            if (optionalDoctor.isEmpty()) {
//                log.error("Ошибка добавления! Не найден врач с id {}", doctorId);
//                throw new NotFoundException("Доктор с ID " + " не найден!", "DNFID-0087");
//            }
//        }
//    }
//
//    public void appointTicketDoctor(Long ticketId, Long doctorId) {
//        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
//        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
//
//        if (optionalTicket.isPresent() && optionalDoctor.isPresent()) {
//            Ticket ticket = optionalTicket.get();
//            Doctor doctor = optionalDoctor.get();
//
//            ticket.setDoctor(doctor);
//            ticketRepository.save(ticket);
//            log.info("Успешное обновление информации!");
//        } else {
//            if (optionalTicket.isEmpty()) {
//                log.error("Ошибка обновления! Не найден талон с id {}", ticketId);
//                throw new NotFoundException("Билет с ID " + ticketId + " не найден!", "TNFID-0089");
//            }
//            if (optionalDoctor.isEmpty()) {
//                log.error("Ошибка добавления! Не найден врач с id {}", doctorId);
//                throw new NotFoundException("Доктор с ID " + " не найден!", "DNFID-0087");
//            }
//        }
//    }

    public Patient createPatient(String fullNme, Date birthDate) {
        Patient createPatient = new Patient(fullNme, birthDate);
        if (!fullNme.isEmpty() && !birthDate.equals(null)) {

            patientRepository.save(createPatient);
            log.info("Успешное сохранение пациента в БД");
        } else {
            if (fullNme.isEmpty()) {
                log.error("Ошибка сохранение пациента в БД!!! Передано пустое поле в ФИО!");
                throw new NullUserException("Имя не может быть пустым! Назначьте ФИО пациента", "EFN-0079");
            }
            if (birthDate.equals(null)) {
                log.error("Ошибка сохранение пациента в БД!!! Передано пустое поле в дату рождения!");
                throw new NullUserException("Дата рождения не может быть пустой! Назначьте дату", "EDB-0078");
            }
        }
        return createPatient;
    }

    public void deletePatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            patientRepository.deleteById(id);
        } else {
            throw new NotFoundException("Пациент с ID " + id + " не найден!", "NFDID-0078");
        }
    }

    public Optional<Patient> updatePatient(Long id, Date updatedBirthDate, String updatedFullName) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            patient.get().setBirthDate(updatedBirthDate);
            patient.get().setFullName(updatedFullName);
            patientRepository.save(patient.get());
            return patient;
        } else {
            throw new NotFoundException("Пациент с ID " + id + " не найден!", "NFDID-0077");
        }
    }

    public List<Patient> getAllPatients() {
        if (!patientRepository.findAll().isEmpty()) {
            return patientRepository.findAll();
        } else {
            throw new UsersNotFoundException("Список пациентов пуст!", "NFDL-0076");
        }
    }
}