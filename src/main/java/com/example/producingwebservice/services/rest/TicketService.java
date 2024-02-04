package com.example.producingwebservice.services.rest;

import com.example.producingwebservice.entity.Doctor;
import com.example.producingwebservice.entity.Patient;
import com.example.producingwebservice.entity.Ticket;
import com.example.producingwebservice.exception.NotFoundException;
import com.example.producingwebservice.repository.DoctorRepository;
import com.example.producingwebservice.repository.PatientRepository;
import com.example.producingwebservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final TicketRepository ticketRepository;

    public void appointTicket(Long ticketId, Long patientId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);

        if (optionalTicket.isPresent() && optionalPatient.isPresent()) {
            Ticket ticket = optionalTicket.get();
            Patient patient = optionalPatient.get();

            ticket.setPatient(patient);
            ticketRepository.save(ticket);
            log.info("Успешное обновление информации!");
        } else {
            if (optionalTicket.isEmpty()) {
                log.error("Ошибка обновления! Не найден талон с id {}", ticketId);
                throw new NotFoundException("Билет с ID " + ticketId + " не найден!", "TNFID-0089");
            }
            if (optionalPatient.isEmpty()) {
                log.error("Ошибка обновления! Не найден пациент с id {}", patientId);
                throw new NotFoundException("Пациент с ID " + patientId + " не найден!", "PNFID-0088");
            }
        }
    }

    public void appointTicketDoctor(Long ticketId, Long doctorId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);

        if (optionalTicket.isPresent() && optionalDoctor.isPresent()) {
            Ticket ticket = optionalTicket.get();
            Doctor doctor = optionalDoctor.get();

            ticket.setDoctor(doctor);
            ticketRepository.save(ticket);
            log.info("Успешное обновление информации!");
        } else {
            if (optionalTicket.isEmpty()) {
                log.error("Ошибка обновления! Не найден талон с id {}", ticketId);
                throw new NotFoundException("Билет с ID " + ticketId + " не найден!", "TNFID-0089");
            }
            if (optionalDoctor.isEmpty()) {
                log.error("Ошибка добавления! Не найден врач с id {}", doctorId);
                throw new NotFoundException("Доктор с ID " + " не найден!", "DNFID-0087");
            }
        }
    }

    public List<Date> getTicketsByDate(Long id, Date date) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isPresent()) {
            log.info("Успешное получение всех слотов!");
            return doctor.map(d -> d.getTicket().stream()
                            .filter(ticket -> ticket.getPatient() == null && isSameDate(ticket.getStartTime(), date))
                            .map(Ticket::getStartTime)
                            .collect(Collectors.toList()))
                    .orElse(Collections.emptyList());
        } else {
            log.error("Ошибка получения слотов! Не найден врач с id {}", id);
            throw new NotFoundException("Врач с ID " + id + " не найден!", "NFDID-0069");
        }
    }

    private boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    public List<Date> getAllTicketByPatientId(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isEmpty()) {
            log.error("Ошибка получения слотов для пациента! Не найден пациент с id {}", id);
            throw new NotFoundException("Пациент с ID " + id + " не найден!", "NFDID-0068");
        } else {
            log.info("Успешное получение слотов!");
            return patientRepository.findById(id)
                    .map(Patient::getTicket)
                    .map(tickets -> tickets.stream()
                            .map(Ticket::getStartTime)
                            .collect(Collectors.toList()))
                    .orElse(Collections.emptyList());
        }
    }
}
