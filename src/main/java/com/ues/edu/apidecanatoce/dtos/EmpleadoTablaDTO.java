package com.ues.edu.apidecanatoce.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoTablaDTO {
    private String dui;
    private String nombre;
    private String apellido;
    private String telefono;
    private String licencia;
    private String tipo_licencia;
    private LocalDate fecha_licencia;
    private String estado;
    private String jefe;
    private String correo;
    private String nombrefoto;
    private String urlfoto;
    private String cargo;
    private String departamento;

}
