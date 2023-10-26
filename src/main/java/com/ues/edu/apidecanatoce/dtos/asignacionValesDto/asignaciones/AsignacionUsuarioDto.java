package com.ues.edu.apidecanatoce.dtos.asignacionValesDto.asignaciones;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AsignacionUsuarioDto {
    private AsignacionValeInDto asignacionValeInDto;
    private String idUsuarioLogueado;
    private String empleado;
    private String cargo;

}
