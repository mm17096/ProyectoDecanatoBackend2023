package com.ues.edu.apidecanatoce.dtos.asignacionValesDto.vales;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DevolucionValeUsuarioDto {
    private DevolucionValeDto valeDevuelto;
    private String usuario;
}
