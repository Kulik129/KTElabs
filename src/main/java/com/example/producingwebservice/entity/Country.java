package com.example.producingwebservice.entity;


import io.spring.guides.gs_producing_web_service.Currency;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "country", propOrder = {
        "id",
        "name",
        "population",
        "capital",
        "currency"
})
@Getter
@Setter
public class Country {
    private Long id;
    @XmlElement(required = true)
    private String name;

    private int population;
    @XmlElement(required = true)
    private String capital;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    private Currency currency;
}

