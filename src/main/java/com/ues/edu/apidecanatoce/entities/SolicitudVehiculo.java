package com.ues.edu.apidecanatoce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_solicitud_vehiculo")
public class SolicitudVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_solicitud_vehiculo")
    private int codigoSolicitudVehiculo;

    @Column(name = "fecha_solicitud")
    private LocalDate fechaSolicitud;

    @Column(name = "objetivo")
    private String objetivoMision;

    @Column(name = "lugar_mision")
    private String lugarMision;

    @Column(name = "fecha_uso")
    private String fechaUso;

    @Column(name = "cantidad_personas")
    private int cantidadPersonas;

    @Column(name = "estado")
    private int estado;

}
