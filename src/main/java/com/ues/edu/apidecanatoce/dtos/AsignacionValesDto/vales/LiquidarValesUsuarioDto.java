package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.vales;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LiquidarValesUsuarioDto {
    private LiquidarValesDto valesLiquidados;
    private String usuario;
    private String empleado;
    private String cargo;

}
