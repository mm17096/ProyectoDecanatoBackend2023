package com.ues.edu.apidecanatoce.dtos.asignacionValesDto.solicitudes;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SolicitudAprobarUsuarioDto {
    private SolicitudValeAprobarDto solicitudValeAprobarDto;
    private String idUsuarioLogueado;
    private String cargo;
}
