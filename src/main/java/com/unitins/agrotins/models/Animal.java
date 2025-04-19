package com.unitins.agrotins.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
public class Animal extends PanacheEntityBase {
    @Id
    public String tagRFID; // ID única do animal (vinda do RFID)

    public String nome;
    public String raca;
    public String dataNascimento;
}