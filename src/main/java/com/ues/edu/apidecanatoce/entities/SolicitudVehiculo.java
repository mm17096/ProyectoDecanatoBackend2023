package com.ues.edu.apidecanatoce.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_solicitud")
    private LocalDate fechaSolicitud;

    @Column(name = "objetivo")
    private String objetivoMision;

    @Column(name = "lugar_mision")
    private String lugarMision;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_uso")
    private String fechaUso;

    @Column(name = "cantidad_personas")
    private int cantidadPersonas;

    @Column(name = "estado")
    private int estado;

}
