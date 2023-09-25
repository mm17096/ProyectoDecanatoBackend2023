package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoActualizarEstadoDTO;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoDto;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoPeticionDtO;
import com.ues.edu.apidecanatoce.entities.estados.Estados;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.ISolicitudValeRepository;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SolicitudVehiculoServicesImpl implements ISolicitudVehiculoServices {

    private final ISolicitudVehiculoRepository solicitudVehiculoServices;
    private final IEstadosRepository estadosRepository;
    private final IVehiculoRepository vehiculoRepository;
    private final IEmpleadoRepository empleadoRepository;
    private final IUsuarioRepository usuarioRepository;
    private final ISolicitudValeRepository solicitudValeRepository;
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
    public List<SolicitudVehiculoPeticionDtO> listarPorPlaca(String codigoplaca) {
        List<SolicitudVehiculo> solicitud=this.solicitudVehiculoServices.findByVehiculoPlaca(codigoplaca);
        return solicitud.stream().map(SolicitudVehiculo::toDto).toList();
    }

    @Override
    public List<SolicitudVehiculoPeticionDtO> listarSinPagina() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtener el ID del usuario autenticado
        String username = authentication.getName();
        String userId = usuarioRepository.findIdByUsername(username);

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtener el ID del usuario autenticado
        String username = authentication.getName();
        String userId = usuarioRepository.findIdByUsername(username);

        List<SolicitudVehiculo> listSoliVe = solicitudVehiculoServices.findByUsuarioCodigoUsuarioAndEstado(userId, id);

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
        SolicitudVehiculoPeticionDtO buscarSoliVe = leerPorId(codigoSolicitudVehiculo);
        data.setEstado(3);
        data.setCodigoSolicitudVehiculo(codigoSolicitudVehiculo);
        return solicitudVehiculoServices.save(data.toEntityComplete(vehiculoRepository, empleadoRepository,
                usuarioRepository)).toDto();
    }

    @Override
    public SolicitudVehiculoActualizarEstadoDTO updateEstado(SolicitudVehiculoActualizarEstadoDTO data) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtener el ID del usuario autenticado
        String userName = authentication.getName();
        Optional<Usuario> user = usuarioRepository.findByNombre(userName);
        String jefeDeptoA = "";
        String rol = "";
        int estado = 0;

        SolicitudVehiculo solicitudExistente =
                solicitudVehiculoServices.findById(data.getCodigoSolicitudVehiculo()).orElseThrow(
                        () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró la solicitud de vehículo"));

        if (user.isPresent()){
            Usuario usuario = user.get();
            jefeDeptoA = usuario.getEmpleado().getNombre() + " "+ usuario.getEmpleado().getApellido();
            rol = String.valueOf(usuario.getRole());
        }else{
            System.out.println("USUARIO VACIO");
        }

        if (Objects.equals(rol, "JEFE_DEPTO") ||
                Objects.equals(rol, "JEFE_FINANACIERO") ||
                (Objects.equals(rol, "DECANO") && data.getEstado() == 1 )) {
            solicitudExistente.setJefeDepto(jefeDeptoA);
            estado = 2;
        } else if (Objects.equals(rol, "SECR_DECANATO")) {
            solicitudExistente.setJefeDepto(solicitudExistente.getJefeDepto());
            estado = 3;
        } else if (Objects.equals(rol, "DECANO") && data.getEstado() == 3) {
            solicitudExistente.setJefeDepto(solicitudExistente.getJefeDepto());
            estado = 4;
        }

        if (data.getEstado() == 6){
            solicitudExistente.setEstado(6);
        } else if(data.getEstado() == 15){
            solicitudExistente.setEstado(15);
        }else{
            solicitudExistente.setEstado(estado);
        }
        solicitudVehiculoServices.save(solicitudExistente);
        return SolicitudVehiculoActualizarEstadoDTO.builder()
                .codigoSolicitudVehiculo(solicitudExistente.getCodigoSolicitudVehiculo())
                .estado(solicitudExistente.getEstado()).build();
    }

    @Override
    public List<SolicitudVehiculoPeticionDtO> listarSinPaginaRol(String rol) {

        int estadoFilter = 0;
        int estadoRevision = 6;
        int estadoDecanoJefe = 1;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtener el ID del usuario autenticado
        String userName = authentication.getName();
        Optional<Usuario> user = usuarioRepository.findByNombre(userName);
        String depto = "";

        if (user.isPresent()){
            Usuario usuario = user.get();
            depto = usuario.getEmpleado().getDepartamento().getNombre();
        }else{
            System.out.println("USUARIO VACIO");
        }

        if (Objects.equals(rol, "JEFE_DEPTO") || Objects.equals(rol, "JEFE_FINANACIERO")) {
            estadoFilter = 1;
        } else if (Objects.equals(rol, "SECR_DECANATO")) {
            estadoFilter = 2;
        } else if (Objects.equals(rol, "DECANO")) {
            estadoFilter = 3;
        }

        List<SolicitudVehiculo> solicitudVehiculos;
        if (Objects.equals(rol, "DECANO")){
            solicitudVehiculos = solicitudVehiculoServices.findByAllSecreDec(estadoFilter, estadoDecanoJefe);
        }else if(Objects.equals(rol, "SECR_DECANATO")){
            solicitudVehiculos = solicitudVehiculoServices.findByAllSecreDec(estadoFilter, estadoRevision);
        }else{
            solicitudVehiculos = solicitudVehiculoServices.findAllByEstadoAndUsuarioEmpleadoDepartamentoNombre(estadoFilter, depto);
        }
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
}
