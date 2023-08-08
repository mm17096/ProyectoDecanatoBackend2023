package com.ues.edu.apidecanatoce.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_empleado")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigoEmpleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="codigo_enpleado")
    private int codigoEmpleado;

    @Column(name ="nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dui")
    private String dui;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "licencia")
    private String licencia;

}
