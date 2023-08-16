package com.ues.edu.apidecanatoce.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_empleado")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "DUI")
public class Empleado {
    @Id
    @Column(name ="DUI")
    private String DUI;

    @Column(name ="nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "licencia")
    private String licencia;

    @Column(name = "tipo_licencia")
    private String tipo_licencia;

    @Column(name = "fecha_licencia")
    private LocalDate fecha_licencia;

    @Column(name = "estado")
    private int estado;

    @Column(name = "jefe")
    private boolean jefe;

    @Column(name = "correo")
    private String correo;

    @Column(name = "nombrefoto")
    private String nombrefoto;

    @Column(name = "urlfoto")
    private String urlfoto;

    @ManyToOne
    @JoinColumn(name = "id_cargo", nullable = false,
            foreignKey = @ForeignKey(name = "FK_empleado_cargo"))
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "id_departamento", nullable = false,
            foreignKey = @ForeignKey(name = "FK_empleado_departamento"))
    private Departamento departamento;

}
