package com.example.producingwebservice.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketRequest {
    private Long ticketId;
    private Long patientId;
    private Long doctorId;
}
