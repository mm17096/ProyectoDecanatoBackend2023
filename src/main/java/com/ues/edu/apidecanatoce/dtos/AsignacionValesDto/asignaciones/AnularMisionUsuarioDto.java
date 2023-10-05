package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.asignaciones;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnularMisionUsuarioDto {
    private AnularMisionDto misionAnulada;
    private String usuario;
    private String empleado;
}
