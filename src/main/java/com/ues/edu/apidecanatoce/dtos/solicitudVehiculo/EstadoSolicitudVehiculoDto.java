package com.ues.edu.apidecanatoce.dtos.solicitudVehiculo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class EstadoSolicitudVehiculoDto {

    private UUID idSolicitudVehiculo;
    private int estado;

}
