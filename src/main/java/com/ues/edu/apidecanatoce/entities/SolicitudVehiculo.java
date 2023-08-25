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
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_solicitud_vehiculo")
public class SolicitudVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_solicitud_vehiculo")
    private UUID codigoSolicitudVehiculo;

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
    @Column(name = "unidad", length = 50)
    private String unidadSolicitante;

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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Pasajeros> listaPasajeros;

    //Responsable de la solicitud
    @ManyToOne
    @JoinColumn(name = "codigo_usuario", nullable = false,
            foreignKey = @ForeignKey(name = "FK_solicitud_vehiculo_usuario"))
    private Usuario usuario;

    //nombre del jefe que aprobo,se obtendra segun el usuario que apruebe
    @Column(name = "jefe_depto", length = 150)
    private String jefeDepto;

    //Feccha de regreso de la mision
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;

    // para manejar los estados que va pasando la solicitud
    @Column(name = "estado", nullable = false)
    private int estado;

    // Mortorista asignado
    @ManyToOne
    @JoinColumn(name = "DUI_MOTORISTA", nullable = true,
            foreignKey = @ForeignKey(name = "FK_solicitud_vehiculo_motorista"))
    private Empleado motorista;

    //Lista de documentos que tiene la solicitud de vehiculo
    @OneToMany(mappedBy = "codigoSolicitudVehiculo", cascade = { CascadeType.ALL })
    private List<Documentos> listDocumentos;

    @OneToMany (mappedBy = "solicitudVehiculo", cascade = CascadeType.ALL)
    private Set<SolicitudVale> solicitudVale = new HashSet<>();

}
