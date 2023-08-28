package com.ues.edu.apidecanatoce.dtos.empleados;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.Empleado;
import com.ues.edu.apidecanatoce.entities.Cargos.Cargo;
import com.ues.edu.apidecanatoce.entities.Departamentos.Departamento;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.ICargoRepository;
import com.ues.edu.apidecanatoce.repositorys.IDeptopRepo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class EmpleadoDto {

    private UUID codigoEmpleado;

    @NotBlank(message = "El DUI es obligatorio")
    @Size(max = 10, message = "El DUI debe tener 9 numeros y un guion")
    private String dui;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 5, max = 35, message = "El nombre debe tener entre 5 y 35 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 5, max = 35, message = "El apellido debe tener entre 5 y 35 caracteres")
    private String apellido;

    @NotBlank(message = "El telefono es obligatorio")
    @Size(max = 9, message = "El telefono debe tener 8 numeros y un guion")
    private String telefono;

    @NotBlank(message = "La licencia es obligatoria")
    @Size(min = 10, max = 17, message = "La licencia debe tener entre 10 y 17 caracteres")
    private String licencia;

    @NotBlank(message = "El tipo de licencia es obligatorio")
    private String tipo_licencia;

    @NotNull(message = "La fecha de expiracion es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha_licencia;

    @NotBlank(message = "El estado es obligatorio")
    private int estado;

    @NotBlank(message = "Confirmar si es jefe o no, es obligatorio")
    private boolean jefe;

    @NotBlank(message = "El correo es obligatorio")
    @Size(min = 16, max = 35, message = "La licencia debe tener entre 16 y 35 caracteres")
    private String correo;

    @NotBlank(message = "El nombre de la foto es obligatorio")
    private String nombrefoto;

    @NotBlank(message = "la URL es obligatoria")
    private String urlfoto;

    @NotNull(message = "Cargo es obligatorio")
    private UUID cargo;
    @NotNull(message = "Departamento es obligatorio")
    private UUID departamento;

    public Empleado toEntityComplete(ICargoRepository cargoRepository, IDeptopRepo deptopRepo) {
        Cargo cargobuscar = cargoRepository.findById(this.cargo).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontro cargo"));

        Departamento departamentobuscar = deptopRepo.findById(this.departamento).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontro proveedor"));

        return Empleado.builder().codigoEmpleado(this.codigoEmpleado).dui(this.dui).nombre(this.nombre)
                .apellido(this.apellido).telefono(this.telefono).licencia(this.licencia)
                .tipo_licencia(this.tipo_licencia)
                .fecha_licencia(this.fecha_licencia).estado(this.estado).jefe(this.jefe)
                .correo(this.correo).nombrefoto(this.nombrefoto).urlfoto(this.urlfoto).cargo(cargobuscar)
                .departamento(departamentobuscar).build();
    }
}
