package com.ues.edu.apidecanatoce.services.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudValeDependeDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudValeADto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ISolicitudValeService {
    SolicitudValeDependeDto registrar(SolicitudValeADto data);

    SolicitudValeDependeDto leerPorId(UUID id);

    Page<SolicitudValeDependeDto> listar(Pageable pageable);

    SolicitudValeDependeDto actualizar(UUID id, SolicitudValeADto data);

    SolicitudValeDependeDto eliminar(UUID id);
}
