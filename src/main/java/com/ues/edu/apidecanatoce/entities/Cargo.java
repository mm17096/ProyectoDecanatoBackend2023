package com.ues.edu.apidecanatoce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="codigo_cargo")
    private UUID codigoCargo;

    @Column(name = "nombre_cargo")
    private String nombreCargo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private int estado;



}
