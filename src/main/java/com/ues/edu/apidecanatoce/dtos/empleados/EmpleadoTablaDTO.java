package com.ues.edu.apidecanatoce.dtos.empleados;

import com.ues.edu.apidecanatoce.entities.cargos.Cargo;
import com.ues.edu.apidecanatoce.entities.departamentos.Departamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoTablaDTO {
    private UUID codigoEmpleado;
    private String dui;
    private String nombre;
    private String apellido;
    private String telefono;
    private String licencia;
    private String tipolicencia;
    private LocalDate fechalicencia;
    private int estado;

    //private boolean jefe;
    private String correo;
    private String nombrefoto;
    private String urlfoto;
    private Cargo cargo;
    private Departamento departamento;
}
