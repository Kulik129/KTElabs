package com.example.producingwebservice.request;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "date"
})
@XmlRootElement(namespace = "http://example.com", name = "getCountryRequest")
@Getter
@Setter
@NoArgsConstructor
public class GetCountryRequest {
    @XmlElement(namespace = "http://example.com", required = true)
    private Date date;
}

