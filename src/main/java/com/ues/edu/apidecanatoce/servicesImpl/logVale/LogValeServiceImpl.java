package com.ues.edu.apidecanatoce.servicesImpl.logVale;

import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import com.ues.edu.apidecanatoce.repositorys.logVale.ILogValeRepository;
import com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.services.logVale.ILogValeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogValeServiceImpl implements ILogValeService {

    private final ILogValeRepository logValeRepository;

    @Override
    public LogVale registrar(LogVale data) {
        return null;
    }

    @Override
    public LogVale leerPorId(UUID id) {
        return null;
    }

    @Override
    public Page<LogVale> listar(Pageable pageable) {
        return null;
    }

    @Override
    public LogVale actualizar(UUID id, LogVale data) {
        return null;
    }

    @Override
    public LogVale eliminar(UUID id) {
        return null;
    }
}
