package com.ues.edu.apidecanatoce.servicesImpl.solicitudVale;



import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.*;

import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.IAsignacionValeRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import com.ues.edu.apidecanatoce.repositorys.logVale.ILogValeRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVale.*;
import com.ues.edu.apidecanatoce.services.solicitudVale.IConsultaValeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ConsultaValeServiceImpl implements IConsultaValeService {
    private final IConsultaValeRepository consultaValeRepository;
    private final IConsultaCompraRepository consultaCompraRepository;
    private final IConsultaDocumentSoliCarRepository consultaDocumentSoliCarRepository;
    private final IConsultaDocumentValeRepository consultaDocumentValeRepository;
    private final IConsultaEmpleadoRepository consultaEmpleadoRepository;
    private final IConsultaLogSoliVehiRepository consultaLogSoliVehiRepository;
    private final IAsignacionValeRepository asignacionValeRepository;
    private final IValeRepository valeRepository;
    private final ILogValeRepository logValeRepository;
    private  final IConsultaValeGRepository consultaValeGRepository;
    @Override
    public List<ConsultaValeDto> listarSinPagina() {
        List<LogVale> logVales = this.logValeRepository.findAll();
        List<DetalleAsignacionVale> vehiculoList= this.consultaValeRepository.findAll();
        return vehiculoList.stream().map(DetalleAsignacionVale::toDTODetalleC).toList();

    }

    @Override
    public List<ConsultaValeGDto> lisConsultaValeGDto(LocalDate fechaI, LocalDate fechaF) throws IOException {
        if (this.consultaValeGRepository.listarVales(fechaI,fechaF).isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No hay vales para mostrar");
        } else {
            return this.consultaValeGRepository.listarVales(fechaI,fechaF);
        }

    }

    @Override
    public List<ConsultaCompraDto> lisConsultaCompraDto(LocalDate fechaI, LocalDate fechaF) throws IOException {
        if (this.consultaCompraRepository.listarCompraDeVales(fechaI,fechaF).isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No hay vales para mostrar");
        } else {
            return this.consultaCompraRepository.listarCompraDeVales(fechaI,fechaF);
        }
    }

    @Override
    public List<ConsultaCantidadValesDelAlDto> lisConsultaValesDelAlDto(UUID id) throws IOException {
        if (this.consultaValeGRepository.listarValesDelAl(id).isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No hay vales para mostrar");
        } else {
            return this.consultaValeGRepository.listarValesDelAl(id);
        }
    }

    @Override
    public List<ConsultaDocumentSoliCarDto> lisDocumentSolicar(UUID id) throws IOException {
        if (this.consultaDocumentSoliCarRepository.listarDocumentoSoli(id).isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No hay documentos para mostrar");
        } else {
            return this.consultaDocumentSoliCarRepository.listarDocumentoSoli(id);
        }
    }

    @Override
    public List<ConsultaSolisValeIdDto> lisDocumentValeid(UUID id) throws IOException {
        if (this.consultaDocumentValeRepository.listarDocumentoSoliValeid(id).isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No hay documentos para mostrar");
        } else {
            return this.consultaDocumentValeRepository.listarDocumentoSoliValeid(id);
        }
    }

    @Override
    public List<ConsultaSoliValeIdDto> lisDocumentVale(UUID id) throws IOException {
        if (this.consultaDocumentValeRepository.listarDocumentoSoliVale(id).isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No hay documentos para mostrar");
        } else {
            return this.consultaDocumentValeRepository.listarDocumentoSoliVale(id);
        }
    }

    @Override
    public List<ConsultaEmpleadoDto> lisDecano() throws IOException {
        if (this.consultaEmpleadoRepository.listarDecanos().isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No hay Empleados para mostrar");
        } else {
            return this.consultaEmpleadoRepository.listarDecanos();
        }
    }

    @Override
    public List<ConsultaLogSoliVeDto> lisLogSoliVehi(UUID id) throws IOException {
        if (this.consultaLogSoliVehiRepository.listarLogSoliVehi(id).isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No hay datos para mostrar");
        } else {
            return this.consultaLogSoliVehiRepository.listarLogSoliVehi(id);
        }
    }
}
