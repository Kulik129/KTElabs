package com.example.producingwebservice.request.soap;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "startTime",
        "quantity",
        "duration"
})
@XmlRootElement(namespace = "http://example.com", name = "getTicketRequest")
@Getter
@Setter
@NoArgsConstructor
public class GetTicketRequest {
    @XmlElement(namespace = "http://example.com", required = true)
    private Date startTime;
    @XmlElement(namespace = "http://example.com", required = true)
    private int quantity;
    @XmlElement(namespace = "http://example.com", required = true)
    private int duration;
}