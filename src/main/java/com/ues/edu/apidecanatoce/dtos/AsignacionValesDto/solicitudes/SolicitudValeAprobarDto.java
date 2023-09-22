package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.solicitudes;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SolicitudValeAprobarDto {

    private UUID codigoSolicitudVale;

    private Integer cantidadVales;

    private Integer estadoSolicitudVale;

    private String observaciones;

}
