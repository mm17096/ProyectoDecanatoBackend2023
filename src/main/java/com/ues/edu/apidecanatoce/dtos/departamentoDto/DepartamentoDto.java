package com.ues.edu.apidecanatoce.dtos.departamentoDto;

import com.ues.edu.apidecanatoce.entities.departamentos.Departamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class DepartamentoDto {

    private UUID codigoDepto;

    @NotBlank(message = "La nombre del cargo es obligatorio")
    @Size(min = 2, max = 750, message = "El nombre debe tener mas 2 caracteres")
    private String nombre;

    private int estado;

    public Departamento toEntityComplete(){
        return Departamento.builder().codigoDepto(this.codigoDepto).nombre(this.nombre).estado(this.estado).build();
    }


}
