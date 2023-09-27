package com.ues.edu.apidecanatoce.services.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovalepeticionDto;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.SolicitudvaleDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudValeDependeDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudValeADto;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ISolicitudValeService {
    SolicitudValeDependeDto registrar(SolicitudValeADto data);

    SolicitudValeDependeDto leerPorId(UUID id);

    Page<SolicitudValeDependeDto> listar(Pageable pageable);

    SolicitudValeDependeDto actualizar(UUID id, SolicitudValeADto data);

    SolicitudValeDependeDto eliminar(UUID id);

    List<SolicitudValeDependeDto> listarSinPaginas();

    SolicitudVale codigosolicitudvehiculo(UUID id);

    SolicitudVale actualizar_solicitudvale(UUID id, SolicitudVale dto);
}
