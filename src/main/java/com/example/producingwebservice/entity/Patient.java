package com.example.producingwebservice.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlSchemaType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @XmlSchemaType(name = "string")
    private UUID uuid;
    @Column(name = "full_name", length = 100)
    private String fullNme;
    @Column(name = "birth_date", length = 8)
    @XmlSchemaType(name = "string")
    private Date birthDate;
}
