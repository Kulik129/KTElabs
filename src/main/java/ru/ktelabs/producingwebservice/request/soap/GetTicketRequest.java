package ru.ktelabs.producingwebservice.request.soap;


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
@XmlRootElement(namespace = "https://ktelabs.ru", name = "setTicketRequest")
@Getter
@Setter
@NoArgsConstructor
public class GetTicketRequest {
    @XmlElement(namespace = "https://ktelabs.ru", required = true)
    private Date startTime;
    @XmlElement(namespace = "https://ktelabs.ru", required = true)
    private int quantity;
    @XmlElement(namespace = "https://ktelabs.ru", required = true)
    private int duration;
}