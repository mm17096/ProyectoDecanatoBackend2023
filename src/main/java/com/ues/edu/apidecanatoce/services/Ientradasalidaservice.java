package com.ues.edu.apidecanatoce.services;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaDto;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaPeticionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface Ientradasalidaservice {
    EntradasalidaPeticionDto registrar(EntradasalidaDto data);

    EntradasalidaPeticionDto leerPorId(UUID id);

    Page<EntradasalidaPeticionDto> listar(Pageable pageable);

    List<EntradasalidaDto> listarSinPagina();

    EntradasalidaDto actualizar(UUID id, EntradasalidaDto data);

    EntradasalidaDto eliminar(UUID id);

}
