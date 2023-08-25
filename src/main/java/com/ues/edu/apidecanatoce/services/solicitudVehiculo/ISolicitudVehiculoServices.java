package com.ues.edu.apidecanatoce.services.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoDto;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoPeticionDtO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ISolicitudVehiculoServices {
    SolicitudVehiculoPeticionDtO registrar(SolicitudVehiculoDto data);
    SolicitudVehiculoPeticionDtO leerPorId(UUID id);

    Page<SolicitudVehiculoDto> listar(Pageable pageable);

    SolicitudVehiculoPeticionDtO modificar(UUID codigoSolicitudVehiculo, SolicitudVehiculoDto data);
}
