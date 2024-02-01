package com.example.producingwebservice.response;


import com.example.producingwebservice.entity.Country;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "country"
})
@XmlRootElement(name = "getCountryResponse")
@Getter
@Setter
public class GetCountryResponse {
    @XmlElement(required = true)
    protected Country country;
}