package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.*;
import com.ues.edu.apidecanatoce.entities.estados.Estados;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.LogSolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.empleado.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.repositorys.estados.IEstadosRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ILogSoliVeRepository;
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
import java.time.LocalDateTime;
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
    private final ILogSoliVeRepository logSoliVeRepository;

    @Override
    public SolicitudVehiculoPeticionDtO registrar(SolicitudVehiculoDto data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LocalDate fechaActual = LocalDate.now();
        String nombreUsuario = obtenerUsuarioAutenticado(authentication);

        data.setFechaSolicitud(fechaActual);
        data.setEstado(1);

        SolicitudVehiculo soliRegistrada = solicitudVehiculoServices.save(data.toEntityComplete(vehiculoRepository, empleadoRepository, usuarioRepository));

        LogSolicitudVehiculo logSoliVe = new LogSolicitudVehiculo();
        logSoliVe.setEstadoLogSolive(1);
        logSoliVe.setFechaLogSoliVe(LocalDateTime.now());
        logSoliVe.setActividad("Solicitud de vehículo realizada");
        logSoliVe.setUsuario(nombreUsuario);
        logSoliVe.setSoliVe(soliRegistrada);
        logSoliVeRepository.save(logSoliVe);
        return soliRegistrada.toDto();
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

        List<SolicitudVehiculo> solicitudVehiculos = solicitudVehiculoServices.findByUsuarioCodigoUsuarioOrderByFechaSalidaDesc(userId);
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
    /*@Override
    public EstadoSolicitudVehiculoDto actualizarEstadoSolcitudVehiculo(UUID id, int estado) {
        SolicitudVehiculo solicitudVehiculo = this.solicitudVehiculoServices.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro la solicitudVehiculo"));

        if (solicitudVehiculo != null) {
            solicitudVehiculo.setEstado(estado);
            return solicitudVehiculoServices.save(solicitudVehiculo).toEstadoSolicitudVehiculoDto();
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo actualizar la solicitud");
        }
    }*/
    @Override
    public List<SolicitudVehiculoPeticionDtO> listarPorEstadoSinPagina(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtener el ID del usuario autenticado
        String username = authentication.getName();
        String userId = usuarioRepository.findIdByUsername(username);

        List<SolicitudVehiculo> listSoliVe = solicitudVehiculoServices.findByUsuarioCodigoUsuarioAndEstadoOrderByFechaSalidaDesc(userId, id);

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
    public SolicitudVehiculoPeticionDtO modificar(UUID codigoSolicitudVehiculo, ActualizacionSecretariaDTO data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String nombreUsuario = obtenerUsuarioAutenticado(authentication);

        SolicitudVehiculoPeticionDtO buscarSoliVe = leerPorId(codigoSolicitudVehiculo);
        data.setEstado(3);
        data.setCodigoSolicitudVehiculo(codigoSolicitudVehiculo);

        LogSoliVeDTO logSoliVe = new LogSoliVeDTO();
        logSoliVe.setEstadoLogSolive(3);
        logSoliVe.setFechaLogSoliVe(LocalDateTime.now());
        logSoliVe.setActividad("Modificacíon y asignación de motorista a la solicitud de vehículo");
        logSoliVe.setUsuario(nombreUsuario);
        logSoliVe.setSoliVe(codigoSolicitudVehiculo);
        logSolicitudVehiculo(logSoliVe);

        return solicitudVehiculoServices.save(data.toEntityComplete(vehiculoRepository, empleadoRepository,
                usuarioRepository)).toDto();
    }

    @Override
    public SolicitudVehiculoActualizarEstadoDTO updateEstado(SolicitudVehiculoActualizarEstadoDTO data) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LogSoliVeDTO logSoliVe = new LogSoliVeDTO();
        String actividad = "";

        // Obtener el ID del usuario autenticado
        String userName = authentication.getName();
        Optional<Usuario> user = usuarioRepository.findByNombre(userName);
        String nombreCompletoUser = "";
        String rol = "";
        int estado = 0;

        SolicitudVehiculo solicitudExistente =
                solicitudVehiculoServices.findById(data.getCodigoSolicitudVehiculo()).orElseThrow(
                        () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró la solicitud de vehículo"));

        if (user.isPresent()){
            Usuario usuario = user.get();
            nombreCompletoUser = usuario.getEmpleado().getNombre() + " "+ usuario.getEmpleado().getApellido();
            rol = String.valueOf(usuario.getRole());
        }else{
            System.out.println("USUARIO VACIO");
        }

        if (Objects.equals(rol, "JEFE_DEPTO") ||
                Objects.equals(rol, "JEFE_FINANACIERO") ||
                (Objects.equals(rol, "DECANO") && data.getEstado() == 1 )) {
            solicitudExistente.setJefeDepto(nombreCompletoUser);
            estado = 2;
            actividad = "Solicitud de vehículo aprobada por jefe de departamento";
        } else if (Objects.equals(rol, "SECR_DECANATO")) {
            solicitudExistente.setJefeDepto(solicitudExistente.getJefeDepto());
            estado = 3;
            actividad = "Asignación de vehiculo realizada";
        } else if (Objects.equals(rol, "DECANO") && data.getEstado() == 3) {
            solicitudExistente.setJefeDepto(solicitudExistente.getJefeDepto());
            estado = 4;
            actividad = "Solicitud de vehículo aprobada por decano";
        }

        if (data.getEstado() == 6){
            solicitudExistente.setEstado(6);
            solicitudExistente.setObservaciones(data.getObservaciones());
            actividad = "Solicitud de vehículo enviada a revisión por decano";
        } else if(data.getEstado() == 15){
            if (Objects.equals(rol, "SECR_DECANATO")){
                solicitudExistente.setEstado(15);
                solicitudExistente.setObservaciones(data.getObservaciones());
                actividad = "Solicitud de vehículo anulada por secretaria";
            } else if (Objects.equals(rol, "DECANO")){
                solicitudExistente.setEstado(15);
                solicitudExistente.setObservaciones(data.getObservaciones());
                actividad = "Solicitud de vehículo anulada por decano";
            }

        }else{
            solicitudExistente.setEstado(estado);
        }

        solicitudVehiculoServices.save(solicitudExistente);
        logSoliVe.setEstadoLogSolive(solicitudExistente.getEstado());
        logSoliVe.setFechaLogSoliVe(LocalDateTime.now());
        logSoliVe.setUsuario(nombreCompletoUser);
        logSoliVe.setSoliVe(solicitudExistente.getCodigoSolicitudVehiculo());
        logSoliVe.setActividad(actividad);
        logSolicitudVehiculo(logSoliVe);

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
            solicitudVehiculos = solicitudVehiculoServices.findByAllDec(estadoFilter, estadoDecanoJefe, depto);
        }else if(Objects.equals(rol, "SECR_DECANATO")){
            solicitudVehiculos = solicitudVehiculoServices.findByAllSecre(estadoFilter, estadoRevision);
        }else{
            solicitudVehiculos =
                    solicitudVehiculoServices.findAllByEstadoAndUsuarioEmpleadoDepartamentoNombreOrderByFechaSalidaDesc
                            (estadoFilter, depto);
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

    @Override
    public List<SolicitudVehiculoPeticionDtO> listarTodas() {
        List<SolicitudVehiculo> solicitud=this.solicitudVehiculoServices.findAll();
        return solicitud.stream().map(SolicitudVehiculo::toDto).toList();
    }

    @Override
    public LogSoliVeDTO logSolicitudVehiculo(LogSoliVeDTO data) {


        SolicitudVehiculo soliVeh = this.solicitudVehiculoServices.findById(data.getSoliVe()).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro la solcitud de vehículo"));
        LogSolicitudVehiculo logSoliVe = new LogSolicitudVehiculo();

        logSoliVe.setEstadoLogSolive(data.getEstadoLogSolive());
        logSoliVe.setFechaLogSoliVe(data.getFechaLogSoliVe());
        logSoliVe.setActividad(data.getActividad());
        logSoliVe.setUsuario(data.getUsuario());
        logSoliVe.setSoliVe(soliVeh);

        try {
            logSoliVeRepository.save(logSoliVe);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se pudo realizar el log");
        }
        return data;
    }

    private String obtenerUsuarioAutenticado(Authentication authentication) {
        String userName = authentication.getName();
        Optional<Usuario> user = usuarioRepository.findByNombre(userName);
        String nombreUsuario= "";

        if (user.isPresent()){
            Usuario usuario = user.get();
            nombreUsuario = usuario.getEmpleado().getNombre() + " "+ usuario.getEmpleado().getApellido();
        }else{
            System.out.println("USUARIO VACIO");
        }
        return nombreUsuario;
    }

    @Override
    public SoliVeActulizarFechaEntradaDTO updateFechaEntrada(SoliVeActulizarFechaEntradaDTO fechaEntradaSoliVeDTO) {

        SolicitudVehiculo solicitudExistente =
                solicitudVehiculoServices.findById(fechaEntradaSoliVeDTO.getCodigoSolicitudVehiculo()).orElseThrow(
                        () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró la solicitud de vehículo"));

        solicitudExistente.setFechaEntrada(fechaEntradaSoliVeDTO.getFechaEntrada());
        solicitudVehiculoServices.save(solicitudExistente);
        return SoliVeActulizarFechaEntradaDTO.builder()
                .codigoSolicitudVehiculo(solicitudExistente.getCodigoSolicitudVehiculo())
                .fechaEntrada(solicitudExistente.getFechaEntrada())
                .build();
    }
}
