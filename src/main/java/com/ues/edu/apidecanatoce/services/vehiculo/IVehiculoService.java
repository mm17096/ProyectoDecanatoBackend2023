package com.ues.edu.apidecanatoce.services.vehiculo;

import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IVehiculoService{
    VehiculoDto registrar(VehiculoDto data);

    VehiculoDto leerPorId(UUID id);

    Page<VehiculoDto> listar(Pageable pageable);

    List<VehiculoDto> listarSinPagina();

    List<VehiculoDto> listarPorClase();

    VehiculoDto actualizar(VehiculoDto data);

    VehiculoDto eliminar(UUID id);
}
