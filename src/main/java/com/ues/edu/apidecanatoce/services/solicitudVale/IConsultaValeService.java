package com.ues.edu.apidecanatoce.services.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IConsultaValeService {
    List<ConsultaValeDto> listarSinPagina();
    List<ConsultaValeGDto> lisConsultaValeGDto(LocalDate fechaI, LocalDate fechaF) throws IOException;
    List<ConsultaCompraDto> lisConsultaCompraDto(LocalDate fechaI, LocalDate fechaF) throws IOException;
    List<ConsultaCantidadValesDelAlDto> lisConsultaValesDelAlDto(UUID id) throws IOException;
    List<ConsultaDocumentSoliCarDto> lisDocumentSolicar(UUID id) throws IOException;
    List<ConsultaSolisValeIdDto> lisDocumentValeid(UUID id) throws IOException;
    List<ConsultaSoliValeIdDto> lisDocumentVale(UUID id) throws IOException;
    List<ConsultaEmpleadoDto> lisDecano() throws IOException;
    List<ConsultaLogSoliVeDto> lisLogSoliVehi(UUID id) throws IOException;
    List<ConsultaLogSoliVeIDDto> lisLogSoliVehiID(UUID id) throws IOException;
    List<ConsultaLogValeDto> lisLogVale(UUID id) throws IOException;
    List<ConsultaIdCompraDto> lisIdCompra(UUID id) throws IOException;
    List<ConsultaIdValeDto> lisIdVale(UUID id) throws IOException;
    List<ConsultaUsuarioDto> lisUsuario(LocalDate fechaI, LocalDate fechaF) throws IOException;
}
