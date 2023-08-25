package com.ues.edu.apidecanatoce.dtos.DepartamentoDto;

import com.ues.edu.apidecanatoce.entities.Departamentos.Departamento;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class DepartamentoDto {

    private UUID codigoDepto;

    @NotBlank(message = "La nombre del cargo es obligatorio")
    @Size(min = 2, max = 750, message = "El nombre debe tener mas 2 caracteres")
    private String nombre;

    private int estado;

    public Departamento toEntityComplete(){
        return Departamento.builder().codigoDepto(this.codigoDepto).estado(this.estado).build();
    }


}
