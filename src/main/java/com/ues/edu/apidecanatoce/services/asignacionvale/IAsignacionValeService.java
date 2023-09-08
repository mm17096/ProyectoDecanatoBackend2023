package com.ues.edu.apidecanatoce.services.asignacionvale;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public interface IAsignacionValeService {
    AsignacionValeInDto registrar(AsignacionValeInDto data);
    AsignacionValeDto leerPorId(UUID id);
    Page<AsignacionValeDto> listar(Pageable pageable);
    AsignacionValeDto actualizar(UUID id, AsignacionValeDto data);
    AsignacionValeDto eliminar(UUID id);
    AsignacionValeOutDto verAsignacionesById(UUID id);
    DetalleAsignacionDto verDetalleById(UUID id);
    List<IValeAsignarDto> lisIValeAsignarDtos(int cantidadVales) throws IOException;
    ValeModDto actualizarEstadoVale(UUID id, int estadoVale);

    SolicitudValeModDto actualizarEstadoSolicitud(UUID id, int estadoSolicitud);


}
