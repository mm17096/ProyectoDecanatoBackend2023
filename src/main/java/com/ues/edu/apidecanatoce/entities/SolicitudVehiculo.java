package com.ues.edu.apidecanatoce.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_solicitud_vehiculo")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigoSolicitudVehiculo")
public class SolicitudVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_solicitud_vehiculo")
    private int codigoSolicitudVehiculo;

    //Fecha en que se realiza de la solcitud
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;

    //Fecha de misión
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    //Unidad solicitante

    //Vehiculo solicitado
    @ManyToOne
    @JoinColumn(name = "codigo_vehiculo", nullable = false,
            foreignKey = @ForeignKey(name = "FK_solicitudV_vehiculo"))
    private Vehiculo vehiculo;

    //Objetivo de la misión
    @Column(name = "objetivo", nullable = false)
    private String objetivoMision;

    //insititución a visitar
    @Column(name = "lugar_mision", nullable = false)
    private String lugarMision;

    //Lugar que visitará
    @Column(name = "direccion", nullable = false)
    private String direccion;

    //Hora de regreso de la misión
    @DateTimeFormat(pattern = "HH:mm", iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(name = "hora_entrada", nullable = false)
    private LocalTime horaEntrada;

    // Hora de salida de la misión
    @DateTimeFormat(pattern = "HH:mm", iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(name = "hora_salida", nullable = false)
    private LocalTime horaSalida;

    //numero de personas asistira la misión
    @Column(name = "cantidad_personas", nullable = false)
    private int cantidadPersonas;

    // listado de personas que asistiran, si son mas de 5 (incluye el responsable)


    //Responsable de la solicitud
    @ManyToOne
    @JoinColumn(name = "codigo_usuario", nullable = false,
            foreignKey = @ForeignKey(name = "FK_solicitud_vehiculo_usuario"))
    private Usuario usuario;

    //nombre del jefe que aprobo
    @Column(name = "jefe_depto", length = 150)
    private String jefeDepto;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;


    @Column(name = "estado", nullable = false)
    private int estado;



    @ManyToOne
    @JoinColumn(name = "codigo_motorista", nullable = true,
            foreignKey = @ForeignKey(name = "FK_solicitud_vehiculo_motorista"))
    private Empleado motorista;

    @OneToMany(mappedBy = "codigoSolicitudVehiculo", cascade = { CascadeType.ALL })
    private List<Documentos> listDocumentos;



}
