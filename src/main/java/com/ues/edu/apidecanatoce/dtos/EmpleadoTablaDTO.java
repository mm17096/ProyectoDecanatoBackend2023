package com.ues.edu.apidecanatoce.dtos;

import com.ues.edu.apidecanatoce.entities.Cargos.Cargo;
import com.ues.edu.apidecanatoce.entities.Departamentos.Departamento;
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
    private int estado;
    private boolean jefe;
    private String correo;
    private String nombrefoto;
    private String urlfoto;
    private Cargo cargo;
    private Departamento departamento;

}
