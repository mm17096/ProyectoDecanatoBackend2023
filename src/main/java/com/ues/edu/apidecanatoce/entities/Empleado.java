package com.ues.edu.apidecanatoce.entities;

import com.ues.edu.apidecanatoce.entities.Cargos.Cargo;
import com.ues.edu.apidecanatoce.entities.Departamentos.Departamento;
import jakarta.persistence.*;
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
@Entity
@Table(name="tb_empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="codigo_empleado")
    private UUID codigoEmpleado;


    @Column(name = "dui")
    private String dui;

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
