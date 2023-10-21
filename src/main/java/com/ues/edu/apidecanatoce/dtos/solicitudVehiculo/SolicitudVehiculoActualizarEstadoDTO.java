package com.ues.edu.apidecanatoce.dtos.solicitudVehiculo;

import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ISolicitudVehiculoRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Data
@Builder
public class SolicitudVehiculoActualizarEstadoDTO {
    private UUID codigoSolicitudVehiculo;
    private String jefeDepto;
    private int estado;
    private String observaciones;
}
