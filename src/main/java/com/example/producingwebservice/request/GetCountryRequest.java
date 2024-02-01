package com.example.producingwebservice.request;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "name"
})
@XmlRootElement(namespace = "http://example.com", name = "getCountryRequest")
@Getter
@Setter
@NoArgsConstructor
public class GetCountryRequest {
    @XmlElement(namespace = "http://example.com", required = true)
    private Long id;

    @XmlElement(namespace = "http://example.com", required = true)
    protected String name;
}
