package com.ues.edu.apidecanatoce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class SolicitudVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_solicitud_vehiculo")
    private int codigoSolicitudVehiculo;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;

    @Column(name = "objetivo", nullable = false)
    private String objetivoMision;

    @Column(name = "lugar_mision", nullable = false)
    private String lugarMision;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;

    @DateTimeFormat(pattern = "HH:mm", iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(name = "hora_entrada", nullable = false)
    private LocalTime horaEntrada;

    @DateTimeFormat(pattern = "HH:mm", iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(name = "hora_salida", nullable = false)
    private LocalTime horaSalida;

    @Column(name = "cantidad_personas", nullable = false)
    private int cantidadPersonas;

    @Column(name = "estado", nullable = false)
    private int estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_usuario", nullable = false,
            foreignKey = @ForeignKey(name = "FK_solicitud_vehiculo_usuario"))
    @JsonManagedReference
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_motorista", nullable = true,
            foreignKey = @ForeignKey(name = "FK_solicitud_vehiculo_motorista"))
    @JsonManagedReference
    private Empleado motorista;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_vehiculo", nullable = false,
            foreignKey = @ForeignKey(name = "FK_solicitudV_vehiculo"))
    @JsonManagedReference
    private Vehiculo vehiculo;

    @OneToMany(mappedBy = "codigoSolicitudVehiculo", cascade = { CascadeType.ALL })
    @JsonManagedReference
    private List<Documentos> listDocumentos;



}
