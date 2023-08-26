package com.ues.edu.apidecanatoce.services.vehiculo;

import com.ues.edu.apidecanatoce.dtos.MensajeRecord;
import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IVehiculoService{
    MensajeRecord registrar(MultipartFile imagen, VehiculoDto data);

    VehiculoDto leerPorId(UUID id);

    Page<VehiculoDto> listar(Pageable pageable);

    List<VehiculoDto> listarSinPagina();

    List<VehiculoDto> listarPorClase();

    MensajeRecord actualizar(MultipartFile imagen, VehiculoDto data);

    VehiculoDto eliminar(UUID id);
}
