package com.unitins.agrotins.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pesagem extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String tagRFID; // Relacionamento com Animal
    public Double peso;
    public LocalDateTime data;

    @PrePersist
    void prePersist() {
        data = LocalDateTime.now();
    }
}
