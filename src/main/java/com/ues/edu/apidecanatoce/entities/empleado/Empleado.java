package com.ues.edu.apidecanatoce.entities.empleado;

import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoPeticionDto;
import com.ues.edu.apidecanatoce.entities.Cargos.Cargo;
import com.ues.edu.apidecanatoce.entities.Departamentos.Departamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="tb_empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="codigo_empleado")
    private UUID codigoEmpleado;
    @Column(name = "dui", length= 10, unique = true)
    private String dui;
    @Column(name ="nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "licencia")
    private String licencia;

    @Column(name = "tipolicencia")
    private String tipolicencia;

    @Column(name = "fechalicencia")
    private LocalDate fechalicencia;

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


    public EmpleadoPeticionDto toDTO() {
        return EmpleadoPeticionDto.builder().codigoEmpleado(this.codigoEmpleado).dui(this.dui).nombre(this.nombre)
                .apellido(this.apellido).telefono(this.telefono).licencia(this.licencia)
                .tipolicencia(this.tipolicencia)
                .fechalicencia(this.fechalicencia).estado(this.estado).jefe(this.jefe)
                .correo(this.correo).nombrefoto(this.nombrefoto).urlfoto(this.urlfoto)
                .cargo(this.cargo.toDto()).departamento(this.departamento.toDto()).build();
    }
}
