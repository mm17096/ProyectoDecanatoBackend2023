package com.ues.edu.apidecanatoce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_Vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "codigo_vehiculo")
    private Integer codigoVehiculo;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "marca")
    private String marca;
    @Column(name = "clase")
    private String clase;
    @Column(name = "motor")
    private String color;
    @Column(name = "year")
    private String year;
    @Column(name = "capacidad")
    private String capacidad;
    @Column(name = "estado")
    private String estado;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "foto")
    private String foto;
    @Column(name = "numero_chasis")
    private String n_chasis;
    @Column(name = "numero_motor")
    private String n_motor;



    @OneToMany(mappedBy="codigoVehiculo", cascade= { CascadeType.ALL })
    @JsonManagedReference
    private List<SolicitudVehiculo> listVehiculos;
}
