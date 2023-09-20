package com.ues.edu.apidecanatoce.services.solicitudVehiculo;

import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.LogSolicitudVehiculo;

import java.util.UUID;

public interface ILogSoliVeService {
    LogSolicitudVehiculo save(LogSolicitudVehiculo data);

    LogSolicitudVehiculo leerPorId(UUID id);

    LogSolicitudVehiculo actualizar (UUID id, LogSolicitudVehiculo  data);
}
