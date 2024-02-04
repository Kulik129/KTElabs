package com.example.producingwebservice.services.rest;

import com.example.producingwebservice.entity.Doctor;
import com.example.producingwebservice.entity.Patient;
import com.example.producingwebservice.entity.Ticket;
import com.example.producingwebservice.repository.DoctorRepository;
import com.example.producingwebservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public List<Ticket> getDates(Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        List<Ticket> tickets = doctor.get().getTicket();
        return tickets;
    }


    public List<Date> simply(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        List<Ticket> tickets = patient.get().getTicket();
        List<Date> dates = new ArrayList<>();
        for (int i = 0; i < tickets.size(); i++) {
            dates.add(tickets.get(i).getStartTime());
        }
        return dates;
    }

    public List<Date> getTicketsByDate(Long id, Date date) {
        Optional<Doctor> doctor = doctorRepository.findById(id);

        return doctor.map(d -> d.getTicket().stream()
                        .filter(ticket -> ticket.getPatient() == null && isSameDate(ticket.getStartTime(), date))
                        .map(Ticket::getStartTime)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
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
        return patientRepository.findById(id)
                .map(Patient::getTicket)
                .map(tickets -> tickets.stream()
                        .map(Ticket::getStartTime)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
