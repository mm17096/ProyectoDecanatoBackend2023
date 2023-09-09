package com.ues.edu.apidecanatoce.dtos.solicitudVehiculo;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SolicitudVehiculoActualizarEstadoDTO {
    private UUID codigoSolicitudVehiculo;
    private int estado;
}
