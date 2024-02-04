package ru.ktelabs.producingwebservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlSchemaType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "patients")
@NoArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @XmlSchemaType(name = "string")
    private UUID uuid;
    @Column(name = "full_name", length = 100)
    private String fullName;
    @Column(name = "birth_date", length = 8)
    @XmlSchemaType(name = "string")
    private Date birthDate;
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Ticket> ticket;

    public Patient(String fullName, Date birthDate) {
        this.uuid = UUID.randomUUID();
        this.fullName = fullName;
        this.birthDate = birthDate;
    }
}
