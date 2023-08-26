package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoDto;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoPeticionDtO;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ISolicitudVehiculoRepository;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.ISolicitudVehiculoServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SolicitudVehiculoServicesImpl implements ISolicitudVehiculoServices {

    private final ISolicitudVehiculoRepository solicitudVehiculoServices;
    @Override
    public SolicitudVehiculoPeticionDtO registrar(SolicitudVehiculoDto data) {
        return null;
    }

    @Override
    public SolicitudVehiculoPeticionDtO leerPorId(UUID id) {
        return null;
    }

    @Override
    public Page<SolicitudVehiculoDto> listar(Pageable pageable) {
        Page<SolicitudVehiculo> solicitudes = solicitudVehiculoServices.findAll(pageable);
        return solicitudes.map(SolicitudVehiculo::toDto);
    }

    @Override
    public SolicitudVehiculoPeticionDtO modificar(UUID codigoSolicitudVehiculo, SolicitudVehiculoDto data) {
        return null;
    }
}
