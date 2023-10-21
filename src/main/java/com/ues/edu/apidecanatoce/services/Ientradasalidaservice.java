package com.ues.edu.apidecanatoce.services;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.CorreosESDto;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaDto;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaPeticionDto;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.LogSoliVeDTO;
import com.ues.edu.apidecanatoce.entities.entradaSalida.Entrada_Salidas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface Ientradasalidaservice {
    EntradasalidaPeticionDto registrar(EntradasalidaDto data);

    EntradasalidaPeticionDto leerPorId(UUID id);

    Page<EntradasalidaPeticionDto> listar(Pageable pageable);

    List<EntradasalidaDto> listarSinPagina();

    EntradasalidaDto actualizar(UUID id, EntradasalidaDto data);

    EntradasalidaDto eliminar(UUID id);
    Entrada_Salidas listaEstado(int estadi, UUID id);

    LogSoliVeDTO logSolicitudVehiculo(LogSoliVeDTO data);

    List<CorreosESDto> correos() throws IOException;
}
