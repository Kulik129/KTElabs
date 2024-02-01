package com.example.producingwebservice.services;

import com.example.producingwebservice.entity.Ticket;
import com.example.producingwebservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public void createSchedule(Date date, int quantity, int duration) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        for (int i = 0; i < quantity; i++) {
            Ticket ticket = new Ticket();
            Date newD = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            ticket.setStartTime(newD);
            ticketRepository.save(ticket);
            localDateTime = localDateTime.plus(Duration.ofHours(duration));
        }
    }
}
