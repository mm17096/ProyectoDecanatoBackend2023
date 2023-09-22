package com.ues.edu.apidecanatoce.servicesImpl.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaCompraDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaValeDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaValeGDto;
import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.IAsignacionValeRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import com.ues.edu.apidecanatoce.repositorys.logVale.ILogValeRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVale.IConsultaCompraRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVale.IConsultaValeGRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVale.IConsultaValeRepository;
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
}
