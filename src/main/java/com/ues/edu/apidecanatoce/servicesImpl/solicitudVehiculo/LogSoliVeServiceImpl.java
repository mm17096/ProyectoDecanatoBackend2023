package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.LogSoliVeDTO;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoPeticionDtO;
import com.ues.edu.apidecanatoce.entities.estados.Estados;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.LogSolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.estados.IEstadosRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ILogSoliVeRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ISolicitudVehiculoRepository;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.ILogSoliVeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogSoliVeServiceImpl implements ILogSoliVeService {
    private final ILogSoliVeRepository logSoliVeRepository;
    private final ISolicitudVehiculoRepository solicitudVehiculoRepository;
    private final IEstadosRepository estadosRepository;
    @Override
    public LogSolicitudVehiculo save(LogSolicitudVehiculo data) {
        return null;
    }

    @Override
    public LogSolicitudVehiculo leerPorId(UUID id) {
        return null;
    }

    @Override
    public LogSolicitudVehiculo actualizar(UUID id, LogSolicitudVehiculo data) {
        return null;
    }

    @Override
    public List<LogSoliVeDTO> obtenerLog(UUID codigoSolicitudVehiculo) {

        List<LogSolicitudVehiculo> logSoli;

        SolicitudVehiculo solicitudVehiculo = solicitudVehiculoRepository.findById(codigoSolicitudVehiculo).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró la solicitud de vehículo"));

        SolicitudVale solicitudVale = logSoliVeRepository.findBySoliVa(solicitudVehiculo.getCodigoSolicitudVehiculo());

        UUID codigoSoliVe = solicitudVehiculo.getCodigoSolicitudVehiculo();

        if (solicitudVale == null){
            //Solicitud no tiene vale
            logSoli = logSoliVeRepository.findBySoliVe(solicitudVehiculo);
        }else{
            //solicitud tiene vale
            UUID codSoliVa = solicitudVale.getIdSolicitudVale();
            logSoli = logSoliVeRepository.obtenerLogSoliM(codigoSoliVe,
                    codSoliVa);

            logSoli.forEach(solicitud -> {
                if (solicitud.getSoliVe() == null) {
                    solicitud.setSoliVe(solicitudVehiculo);
                }
            });
        }

        List<Estados> estados = estadosRepository.findAll();
        Map<Integer, String> estadoStringMap = new HashMap<>();
        for (Estados estado: estados) {
            estadoStringMap.put(estado.getCodigoEstado(), estado.getNombreEstado());
        }

        return logSoli.stream().map(solicitud -> {
            LogSoliVeDTO dto = solicitud.toLogSoliVeDTO();
            String estadoAsString = estadoStringMap.get(solicitud.getEstadoLogSolive());
            dto.setEstadoString(estadoAsString);
            return dto;
        }).collect(Collectors.toList());

    }
}
