package com.ues.edu.apidecanatoce.services.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.*;
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

    SolicitudVehiculoPeticionDtO modificar(UUID codigoSolicitudVehiculo, ActualizacionSecretariaDTO data);

    List<SolicitudVehiculoPeticionDtO> listarSinPagina();
    List<SolicitudVehiculoPeticionDtO> listarSinPaginaRol(String rol);

    SolicitudVehiculoActualizarEstadoDTO updateEstado(SolicitudVehiculoActualizarEstadoDTO nuevoEstado);

    //EstadoSolicitudVehiculoDto actualizarEstadoSolcitudVehiculo(UUID id, int estado);

    List<SolicitudVehiculoPeticionDtO> listarPorEstadoSinPagina(Integer estado);
    List<SolicitudVehiculoPeticionDtO> listarTodas();

    LogSoliVeDTO logSolicitudVehiculo(LogSoliVeDTO data);
    SoliVeActulizarFechaEntradaDTO updateFechaEntrada(SoliVeActulizarFechaEntradaDTO fechaEntradaSoliVeDTO);

    String obtenerCorreo(String depto);

    String obtenerCorreoNombre(String id);

    String obtenerCorreoNombreRol(String rol);
}
