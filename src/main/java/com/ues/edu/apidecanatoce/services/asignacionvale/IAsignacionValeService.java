package com.ues.edu.apidecanatoce.services.asignacionvale;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.*;
import com.ues.edu.apidecanatoce.entities.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


public interface IAsignacionValeService {
    AsignacionValeInDto registrar(AsignacionValeInDto data);
    AsignacionValeDto leerPorId(UUID id);
    Page<AsignacionValeDto> listar(Pageable pageable);
    DevolucionValeDto devolverVale(DevolucionValeDto data);
    AsignacionValeDto eliminar(UUID id);
    AsignacionValeOutDto verAsignacionesById(UUID id);
    DetalleAsignacionDto verDetalleById(UUID id);
    List<IValeAsignarDto> lisIValeAsignarDtos(int cantidadVales) throws IOException;
    ValeModDto actualizarEstadoVale(UUID id, int estadoVale);

    SolicitudValeModDto actualizarEstadoSolicitud(UUID id, int estadoSolicitud);

    SolicitudVehiculoModDto actualizarEstadoSolicitudVehiculo(UUID id, int estadoSolicitudVehiculo);

    LiquidarValesDto liquidarVales(LiquidarValesDto data);

    AsignacionValeModDto actualizarEstadoAsignacion(UUID id, int estadoAsignacion);

    LogValeDto logVale(LogValeDto data);

    Page<SolicitudVale> listarSolicitudVale(Pageable pageable);




}
