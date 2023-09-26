package com.ues.edu.apidecanatoce.services.asignacionvale;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.asignaciones.*;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.detalles.DetalleAsignacionDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.solicitudes.*;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.vales.*;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


public interface IAsignacionValeService {
    AsignacionValeInDto registrar(AsignacionValeInDto data, String usuario);

    AnularMisionDto anularMision(AnularMisionDto data);
    AsignacionValeDto leerPorId(UUID id);
    Page<AsignacionValeDto> listar(Pageable pageable);
    DevolucionValeDto devolverVale(DevolucionValeDto data);
    AsignacionValeDto eliminar(UUID id);
    AsignacionValeOutDto verAsignacionesById(UUID id);
    DetalleAsignacionDto verDetalleById(UUID id);
    List<IValeAsignarDto> lisIValeAsignarDtos(int cantidadVales) throws IOException;

    Page<IValeAsignarDto> lisIValeAsignarDtosPage(int cantidadVales, Pageable pageable) throws IOException;

    ValeModDto actualizarEstadoVale(UUID id, int estadoVale);

    SolicitudValeModDto actualizarEstadoSolicitud(UUID id, int estadoSolicitud);

    SolicitudValeEstadoEntradaDto actualizarEstadoEntradaSolicitud(UUID id, int estadoSolicitud);

    SolicitudVehiculoModDto actualizarEstadoSolicitudVehiculo(UUID id, int estadoSolicitudVehiculo);

    LiquidarValesDto liquidarVales(LiquidarValesDto data);

    AsignacionValeModDto actualizarEstadoAsignacion(UUID id, int estadoAsignacion);

    LogValeDto logVale(LogValeDto data);

    Page<SolicitudVale> listarSolicitudVale(Pageable pageable);

    CantidadValesDto cantidadVales();

    BuscarSolicitudValeDto codigoSolictudVale(UUID id);

    BuscarAsignacionValeDto codigoAsignacionVale(UUID id);

    BuscarSolicitudVehiculoDto codigoSolicitudVehiculo(UUID id);

    List<ISolicitudValeFiltradasDto> findSolicitudValeByEstado(int estado) throws IOException;

    SolicitudValeAprobarDto actualizarSolicitudAprobar(SolicitudValeAprobarDto data);






}
