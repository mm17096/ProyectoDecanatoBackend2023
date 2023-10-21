package com.ues.edu.apidecanatoce.services.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.LogSoliVeDTO;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.LogSolicitudVehiculo;

import java.util.List;
import java.util.UUID;

public interface ILogSoliVeService {
    LogSolicitudVehiculo save(LogSolicitudVehiculo data);

    LogSolicitudVehiculo leerPorId(UUID id);

    LogSolicitudVehiculo actualizar (UUID id, LogSolicitudVehiculo  data);

    List<LogSoliVeDTO> obtenerLog(UUID codigoSolicitudVehiculo);
}
