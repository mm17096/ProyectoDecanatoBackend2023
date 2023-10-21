package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.solicitudes;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BuscarSolicitudVehiculoDto {

    private UUID codigoSolicitudVehiculo;
}
