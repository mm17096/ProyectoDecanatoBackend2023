package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo;

import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.LogSolicitudVehiculo;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ILogSoliVeRepository;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.ILogSoliVeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogSoliVeServiceImpl implements ILogSoliVeService {
    private final ILogSoliVeRepository logSoliVeRepository;
    @Override
    public LogSolicitudVehiculo save(LogSolicitudVehiculo data) {
        return null;
    }

    @Override
    public LogSolicitudVehiculo leerPorId(UUID id) {
        return null;
    }

    @Override
    public LogSolicitudVehiculo actualizar(UUID id, LogSolicitudVehiculo data) {
        return null;
    }
}
