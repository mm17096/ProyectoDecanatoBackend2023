package com.ues.edu.apidecanatoce.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_vehiculo")
    private Integer codigoVehiculo;
    @Column(name = "placa")
    private String placa;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "marca")
    private String marca;
    @Column(name = "clase")
    private String clase;
    @Column(name = "color")
    private String color;
    @Column(name = "year")
    private int year;
    @Column(name = "fecha_tarjeta")
    private LocalDate fecha_tarjeta;
    @Column(name = "capacidad")
    private Integer capacidad;
    @Column(name = "capacidad_tanque")
    private String capacidadTanque;
    @Column(name = "estado")
    private int estado;
    @Column(name = "numero_chasis")
    private String n_chasis;
    @Column(name = "numero_motor")
    private String n_motor;
    @Column(name = "tipo_gas")
    private String tipo_gas;
    @Column(name = "nombrefoto")
    private String nombrefoto;
    @Column(name = "urlfoto")
    private String urlfoto;
}

