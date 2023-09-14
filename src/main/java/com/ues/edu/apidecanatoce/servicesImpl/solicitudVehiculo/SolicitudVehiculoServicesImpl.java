package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoActualizarEstadoDTO;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoDto;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoPeticionDtO;
import com.ues.edu.apidecanatoce.entities.estados.Estados;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.empleado.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.repositorys.estados.IEstadosRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ISolicitudVehiculoRepository;
import com.ues.edu.apidecanatoce.repositorys.usuario.IUsuarioRepository;
import com.ues.edu.apidecanatoce.repositorys.vehiculo.IVehiculoRepository;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.ISolicitudVehiculoServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SolicitudVehiculoServicesImpl implements ISolicitudVehiculoServices {

    private final ISolicitudVehiculoRepository solicitudVehiculoServices;
    private final IEstadosRepository estadosRepository;
    private final IVehiculoRepository vehiculoRepository;
    private final IEmpleadoRepository empleadoRepository;
    private final IUsuarioRepository usuarioRepository;
    @Override
    public SolicitudVehiculoPeticionDtO registrar(SolicitudVehiculoDto data) {
        LocalDate fechaActual = LocalDate.now();
        data.setFechaSolicitud(fechaActual);
        data.setEstado(1);

        // uniad solicitante
        return solicitudVehiculoServices.save(data.toEntityComplete(vehiculoRepository, empleadoRepository,
                usuarioRepository)).toDto();
    }

    @Override
    public SolicitudVehiculoPeticionDtO leerPorId(UUID id) {
        SolicitudVehiculo solicitudVehiculo = solicitudVehiculoServices.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró la solicitud de vehículo"));
        return solicitudVehiculo.toDto();
    }

    @Override
    public List<SolicitudVehiculoPeticionDtO> listarSinPagina() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtener el ID del usuario autenticado
        String username = authentication.getName();
        String userId = usuarioRepository.findIdByUsername(username);

        System.out.println("El usuario activo es: " + userId);

        List<SolicitudVehiculo> solicitudVehiculos = solicitudVehiculoServices.findByUsuarioCodigoUsuario(userId);
        List<Estados> estados = estadosRepository.findAll();
        Map<Integer, String> estadoStringMap = new HashMap<>();
        for (Estados estado: estados) {
            estadoStringMap.put(estado.getCodigoEstado(), estado.getNombreEstado());
        }

        return solicitudVehiculos.stream().map(solicitud -> {
            SolicitudVehiculoPeticionDtO dto = solicitud.toDto();
            String estadoAsString = estadoStringMap.get(solicitud.getEstado());
            dto.setEstadoString(estadoAsString);
            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public Page<SolicitudVehiculoPeticionDtO> listar(Pageable pageable) {
        Page<SolicitudVehiculo> solicitudes = solicitudVehiculoServices.findAll(pageable);
        List<Estados> estados = estadosRepository.findAll();
        Map<Integer, String> estadoStringMap = new HashMap<>();
        for (Estados estado: estados) {
            estadoStringMap.put(estado.getCodigoEstado(), estado.getNombreEstado());
        }

        return solicitudes.map(solicitud -> {
            SolicitudVehiculoPeticionDtO dto = solicitud.toDto();
            String estadoAsString = estadoStringMap.get(solicitud.getEstado());
            dto.setEstadoString(estadoAsString);
            return dto;
        });
    }

    @Override
    public Page<SolicitudVehiculoPeticionDtO> listarPorEstado(Integer id, Pageable pageable) {
        Page<SolicitudVehiculo> listSoliVe = solicitudVehiculoServices.findAllByEstado(id, pageable);

        List<Estados> estados = estadosRepository.findAll();

        Map<Integer, String> estadoStringMap = new HashMap<>();
        for (Estados estado: estados) {
            estadoStringMap.put(estado.getCodigoEstado(), estado.getNombreEstado());
        }

        return listSoliVe.map(solicitud -> {
            SolicitudVehiculoPeticionDtO dto = solicitud.toDto();
            String estadoAsString = estadoStringMap.get(solicitud.getEstado());
            dto.setEstadoString(estadoAsString);
            return dto;
        });
    }

    @Override
    public List<SolicitudVehiculoPeticionDtO> listarPorEstadoSinPagina(Integer id) {
        List<SolicitudVehiculo> listSoliVe = solicitudVehiculoServices.findAllByEstado(id);

        List<Estados> estados = estadosRepository.findAll();

        Map<Integer, String> estadoStringMap = new HashMap<>();
        for (Estados estado: estados) {
            estadoStringMap.put(estado.getCodigoEstado(), estado.getNombreEstado());
        }

        return listSoliVe.stream().map(solicitud -> {
            SolicitudVehiculoPeticionDtO dto = solicitud.toDto();
            String estadoAsString = estadoStringMap.get(solicitud.getEstado());
            dto.setEstadoString(estadoAsString);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public SolicitudVehiculoPeticionDtO modificar(UUID codigoSolicitudVehiculo, SolicitudVehiculoDto data) {
        ///SolicitudVehiculoPeticionDtO buscarSoliVe = leerPorId(codigoSolicitudVehiculo);
        data.setCodigoSolicitudVehiculo(codigoSolicitudVehiculo);
        return solicitudVehiculoServices.save(data.toEntityComplete(vehiculoRepository, empleadoRepository,
                usuarioRepository)).toDto();
    }

    @Override
    public SolicitudVehiculoActualizarEstadoDTO updateEstado(
            UUID codigoSolicitudVehiculo, SolicitudVehiculoActualizarEstadoDTO nuevoEstado) {
        SolicitudVehiculo solicitudExistente =
                solicitudVehiculoServices.findById(codigoSolicitudVehiculo).orElseThrow(
                        () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró la solicitud de vehículo"));
        solicitudExistente.setEstado(nuevoEstado.getEstado());
        solicitudVehiculoServices.save(solicitudExistente);
        return SolicitudVehiculoActualizarEstadoDTO.builder()
                .codigoSolicitudVehiculo(solicitudExistente.getCodigoSolicitudVehiculo())
                .estado(solicitudExistente.getEstado()).build();
    }
}
