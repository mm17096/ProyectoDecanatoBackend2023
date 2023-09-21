package com.ues.edu.apidecanatoce.services.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaCompraDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaValeDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaValeGDto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IConsultaValeService {
    List<ConsultaValeDto> listarSinPagina();
    List<ConsultaValeGDto> lisConsultaValeGDto(LocalDate fechaI, LocalDate fechaF) throws IOException;
    List<ConsultaCompraDto> lisConsultaCompraDto(LocalDate fechaI, LocalDate fechaF) throws IOException;
}
