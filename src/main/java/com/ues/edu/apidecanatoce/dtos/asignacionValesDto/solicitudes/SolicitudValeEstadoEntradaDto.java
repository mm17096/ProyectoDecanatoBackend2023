package com.ues.edu.apidecanatoce.dtos.asignacionValesDto.solicitudes;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class SolicitudValeEstadoEntradaDto {

    private UUID idSolicitudVale;
    private int estadoEntrada;

}
