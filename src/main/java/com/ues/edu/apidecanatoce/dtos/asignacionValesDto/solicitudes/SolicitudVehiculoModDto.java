package com.ues.edu.apidecanatoce.dtos.asignacionValesDto.solicitudes;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class SolicitudVehiculoModDto {

    private UUID idSolicitudVehiculo;
    private int estadoSolicitudVehiculo;
}
