package com.ues.edu.apidecanatoce.servicesImpl.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaValeDto;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoPeticionDtO;
import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import com.ues.edu.apidecanatoce.entities.estados.Estados;
import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.IAsignacionValeRepository;
import com.ues.edu.apidecanatoce.repositorys.compras.IValeRepository;
import com.ues.edu.apidecanatoce.repositorys.logVale.ILogValeRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVale.IConsultaValeRepository;
import com.ues.edu.apidecanatoce.services.solicitudVale.IConsultaValeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ConsultaValeServiceImpl implements IConsultaValeService {
    private final IConsultaValeRepository consultaValeRepository;
    private final IAsignacionValeRepository asignacionValeRepository;
    private final IValeRepository valeRepository;
    private final ILogValeRepository logValeRepository;
    @Override
    public List<ConsultaValeDto> listarSinPagina() {
        List<LogVale> logVales = this.logValeRepository.findAll();
        List<DetalleAsignacionVale> vehiculoList= this.consultaValeRepository.findAll();
        return vehiculoList.stream().map(DetalleAsignacionVale::toDTODetalleC).toList();
     /*   return vehiculoList.stream().map(consulta ->{
            ConsultaValeDto dto = consulta.toDTODetalleC();
            //if(dto.getValeid() )
            dto.setLogVale(logVales);
            return dto;
        }).toList();*/


    }
}
