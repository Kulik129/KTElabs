package ru.ktelabs.producingwebservice.services.soap;

import ru.ktelabs.producingwebservice.entity.Ticket;
import ru.ktelabs.producingwebservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketServiceS {
    private final TicketRepository ticketRepository;

    public void createSchedule(Date date, int quantity, int duration) {
        try {
            LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            for (int i = 0; i < quantity; i++) {
                Ticket ticket = new Ticket();
                Date newD = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                ticket.setStartTime(newD);
                ticketRepository.save(ticket);
                localDateTime = localDateTime.plus(Duration.ofHours(duration));
            }
            log.info("Успешное создание новых талонов");
        } catch (Exception ex) {
            log.error("Ошибка при создании талонов!");
        }
    }
}
