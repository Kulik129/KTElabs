package com.example.producingwebservice.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
@Entity
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ticket", propOrder = {
        "id",
        "doctor",
        "patient",
        "start_time"
})
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(required = true)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @XmlElement(required = true)
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    @XmlElement(required = true)
    private Patient patient;
    @Column(name = "start_time")
    @XmlElement(required = true)
    private Date startTime;
}