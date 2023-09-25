package com.ues.edu.apidecanatoce.services.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoActualizarEstadoDTO;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoDto;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoPeticionDtO;
import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ISolicitudVehiculoServices {
    SolicitudVehiculoPeticionDtO registrar(SolicitudVehiculoDto data);
    SolicitudVehiculoPeticionDtO leerPorId(UUID id);
    List<SolicitudVehiculoPeticionDtO> listarPorPlaca(String codigoplaca);
    Page<SolicitudVehiculoPeticionDtO> listar(Pageable pageable);
    Page<SolicitudVehiculoPeticionDtO> listarPorEstado(Integer id, Pageable pageable);

    SolicitudVehiculoPeticionDtO modificar(UUID codigoSolicitudVehiculo, SolicitudVehiculoDto data);

    List<SolicitudVehiculoPeticionDtO> listarSinPagina();
    List<SolicitudVehiculoPeticionDtO> listarSinPaginaRol(String rol);

    SolicitudVehiculoActualizarEstadoDTO updateEstado(SolicitudVehiculoActualizarEstadoDTO nuevoEstado);
    List<SolicitudVehiculoPeticionDtO> listarPorEstadoSinPagina(Integer id);
    List<SolicitudVehiculoPeticionDtO> listarTodas();
}
