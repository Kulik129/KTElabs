package com.example.producingwebservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private UUID uuid;
    @Column(name = "full_name")
    private String fullName;
    @OneToMany(mappedBy = "doctor")
    private List<Ticket> ticket;

    public Doctor(String fullName) {
        this.uuid = UUID.randomUUID();
        this.fullName = fullName;
    }
}
