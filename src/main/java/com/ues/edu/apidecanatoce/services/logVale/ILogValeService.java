package com.ues.edu.apidecanatoce.services.logVale;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.vales.LogValeDto;
import com.ues.edu.apidecanatoce.dtos.compras.ValeDependeDto;
import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ILogValeService {
    LogVale registrar(LogVale data);

    LogVale leerPorId(UUID id);

    Page<LogVale> listar(Pageable pageable);

    LogVale actualizar(UUID id, LogVale data);

    List<LogValeDto> obtenerLogValesPorEstadoYMes(int estado);

    LogVale eliminar(UUID id);


}
