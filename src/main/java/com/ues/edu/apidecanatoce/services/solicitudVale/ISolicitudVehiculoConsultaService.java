package com.ues.edu.apidecanatoce.services.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudVahiculoConsultaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISolicitudVehiculoConsultaService {
    Page<SolicitudVahiculoConsultaDto> listar(Pageable pageable);
}
