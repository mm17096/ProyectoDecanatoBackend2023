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
        String userName = authentication.getName();

        String nombreUsuario = obtenerUsuarioAutenticado(authentication);
        String rol = "";
        String nombreCargo = "";
        LogSolicitudVehiculo logSoliVe = new LogSolicitudVehiculo();
        String nombreCompleto ="";


        SolicitudVehiculo soliRegistrada;


        Optional<Usuario> user = usuarioRepository.findByNombre(userName);

        if (user.isPresent()){
            Usuario usuario = user.get();
            rol = String.valueOf(usuario.getRole());
            nombreCargo = usuario.getEmpleado().getCargo().getNombreCargo();
            nombreCompleto = usuario.getEmpleado().getNombre() + " "+ usuario.getEmpleado().getApellido();
        }else{
            System.out.println("USUARIO VACIO");
        }

        if (Objects.equals(rol, "JEFE_DEPTO") || Objects.equals(rol, "JEFE_FINANACIERO") ||
        Objects.equals(rol, "DECANO")){
            data.setEstado(2);
            data.setNombreJefeDepto(nombreCompleto);
            logSoliVe.setEstadoLogSolive(2);
        } else {
            data.setEstado(1);
            logSoliVe.setEstadoLogSolive(1);
        }

        data.setFechaSolicitud(fechaActual);
        data.setTieneVale(true);

        logSoliVe.setUsuario(nombreUsuario);
        logSoliVe.setCargo(nombreCargo);
        logSoliVe.setFechaLogSoliVe(LocalDateTime.now());
        logSoliVe.setActividad("Solicitud de vehículo realizada");
        soliRegistrada = solicitudVehiculoServices.save(data.toEntityComplete(vehiculoRepository, empleadoRepository, usuarioRepository));

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

    @Override
    public List<SolicitudVehiculoPeticionDtO> listarPorEstadoSinPagina(Integer estado) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtener el ID del usuario autenticado
        String username = authentication.getName();
        String userId = usuarioRepository.findIdByUsername(username);
        Optional<Usuario> user = usuarioRepository.findByNombre(username);
        String rol = "";

        if (user.isPresent()){
            Usuario usuario = user.get();
            rol = String.valueOf(usuario.getRole());

        }else{
            System.out.println("USUARIO VACIO");
        }

        List<SolicitudVehiculo> listSoliVe ;
        if ( Objects.equals(rol, "ADMIN") || Objects.equals(rol, "SECR_DECANATO")) {
            listSoliVe = solicitudVehiculoServices.findAllByEstado(estado);
        }else{
            listSoliVe =solicitudVehiculoServices.findByUsuarioCodigoUsuarioAndEstadoOrderByFechaSalidaDesc(userId, estado);
        }


        List<Estados> estados = estadosRepository.findAll();

        Map<Integer, String> estadoStringMap = new HashMap<>();
        for (Estados est: estados) {
            estadoStringMap.put(est.getCodigoEstado(), est.getNombreEstado());
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
        LogSoliVeDTO logSoliVe = new LogSoliVeDTO();

        String nombreUsuario = obtenerUsuarioAutenticado(authentication);

        SolicitudVehiculoPeticionDtO buscarSoliVe = leerPorId(codigoSolicitudVehiculo);

        String userName = authentication.getName();
        Optional<Usuario> user = usuarioRepository.findByNombre(userName);
        String nombreCargo = "";

        if (user.isPresent()){
            Usuario usuario = user.get();
            nombreCargo = usuario.getEmpleado().getCargo().getNombreCargo();
        }else{
            System.out.println("USUARIO VACIO");
        }

        if (buscarSoliVe.getEstado() == 2 || buscarSoliVe.getEstado() == 6){
            //aprobada por el jefe
            data.setEstado(3);
            logSoliVe.setActividad("Modificación y asignación de motorista a la solicitud de vehículo por " + nombreCargo);
            logSoliVe.setEstadoLogSolive(3);
        }else{
            // editada en otro estado
            data.setEstado(buscarSoliVe.getEstado());
            logSoliVe.setActividad("Modificación a la solicitud de vehículo por " + nombreCargo);
            logSoliVe.setEstadoLogSolive(buscarSoliVe.getEstado());
        }
        data.setCodigoSolicitudVehiculo(codigoSolicitudVehiculo);
        logSoliVe.setFechaLogSoliVe(LocalDateTime.now());
        logSoliVe.setUsuario(nombreUsuario);
        logSoliVe.setCargo(nombreCargo);
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
        String nombreCargo= "";

        SolicitudVehiculo solicitudExistente =
                solicitudVehiculoServices.findById(data.getCodigoSolicitudVehiculo()).orElseThrow(
                        () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró la solicitud de vehículo"));

        if (user.isPresent()){
            Usuario usuario = user.get();
            nombreCompletoUser = usuario.getEmpleado().getNombre() + " "+ usuario.getEmpleado().getApellido();
            rol = String.valueOf(usuario.getRole());
            nombreCargo = usuario.getEmpleado().getCargo().getNombreCargo();

        }else{
            System.out.println("USUARIO VACIO");
        }

        if (Objects.equals(rol, "JEFE_DEPTO") ||
                Objects.equals(rol, "JEFE_FINANACIERO") ||
                ((Objects.equals(rol, "DECANO") || Objects.equals(rol, "ADMIN")) && data.getEstado() == 1 ) ) {
            solicitudExistente.setJefeDepto(nombreCompletoUser);
            estado = 2;
            solicitudExistente.setObservaciones(data.getObservaciones());
            actividad = "Solicitud de vehículo aprobada por " + nombreCargo;
        } else if (Objects.equals(rol, "SECR_DECANATO") || (Objects.equals(rol, "ADMIN") && data.getEstado() == 2)) {
            solicitudExistente.setJefeDepto(solicitudExistente.getJefeDepto());
            estado = 3;
            solicitudExistente.setObservaciones(data.getObservaciones());
            actividad = "Asignación de motorista realizada por " +nombreCargo;
        } else if ( (Objects.equals(rol, "DECANO") || Objects.equals(rol, "ADMIN") ) && data.getEstado() == 3) {
            solicitudExistente.setJefeDepto(solicitudExistente.getJefeDepto());
            estado = 4;
            solicitudExistente.setObservaciones(data.getObservaciones());
            actividad = "Solicitud de vehículo con vales aprobada por " + nombreCargo;
        }

        if (data.getEstado() == 6){
            solicitudExistente.setEstado(6);
            solicitudExistente.setObservaciones(data.getObservaciones());
            actividad = "Solicitud de vehículo enviada a revisión por " + nombreCargo;
        } else if(data.getEstado() == 15){
            if (Objects.equals(rol, "SECR_DECANATO")){
                solicitudExistente.setEstado(15);
                solicitudExistente.setObservaciones(data.getObservaciones());
                actividad = "Solicitud de vehículo anulada por "+nombreCargo;
            } else if (Objects.equals(rol, "DECANO") || Objects.equals(rol, "ADMIN")){
                solicitudExistente.setEstado(15);
                solicitudExistente.setObservaciones(data.getObservaciones());
                actividad = "Solicitud de vehículo anulada por "+nombreCargo;
                if (Objects.equals(rol, "ADMIN") && data.getJefeDepto() == null)
                    solicitudExistente.setJefeDepto(nombreCompletoUser);
            }else if (Objects.equals(rol, "JEFE_DEPTO") || Objects.equals(rol, "JEFE_FINANACIERO")){
                solicitudExistente.setEstado(15);
                solicitudExistente.setObservaciones(data.getObservaciones());
                solicitudExistente.setJefeDepto(nombreCompletoUser);
                actividad = "Solicitud de vehículo anulada por " + nombreCargo;
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
        logSoliVe.setCargo(nombreCargo);
        logSolicitudVehiculo(logSoliVe);

        return SolicitudVehiculoActualizarEstadoDTO.builder()
                .codigoSolicitudVehiculo(solicitudExistente.getCodigoSolicitudVehiculo())
                .estado(solicitudExistente.getEstado()).build();
    }

    @Override
    public SolicitudVehiculoActualizarEstadoDTO updateEstadoSinVales(SolicitudVehiculoActualizarEstadoDTO data) {

        SolicitudVehiculo solicitudExistente =
                solicitudVehiculoServices.findById(data.getCodigoSolicitudVehiculo()).orElseThrow(
                        () -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró la solicitud de vehículo"));
        solicitudExistente.setEstado(data.getEstado());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LogSoliVeDTO logSoliVe = new LogSoliVeDTO();

        String userName = authentication.getName();
        Optional<Usuario> user = usuarioRepository.findByNombre(userName);
        String nombreCompletoUser = "";
        String nombreCargo= "";

        if (user.isPresent()){
            Usuario usuario = user.get();
            nombreCompletoUser = usuario.getEmpleado().getNombre() + " "+ usuario.getEmpleado().getApellido();
            nombreCargo = usuario.getEmpleado().getCargo().getNombreCargo();

        }else{
            System.out.println("USUARIO VACIO");
        }

        logSoliVe.setEstadoLogSolive(solicitudExistente.getEstado());
        logSoliVe.setFechaLogSoliVe(LocalDateTime.now());
        logSoliVe.setUsuario(nombreCompletoUser);
        logSoliVe.setSoliVe(solicitudExistente.getCodigoSolicitudVehiculo());
        logSoliVe.setActividad("Solicitud de vehículo sin vales aprobada por " +nombreCompletoUser);
        logSoliVe.setCargo(nombreCargo);
        logSolicitudVehiculo(logSoliVe);

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
        logSoliVe.setCargo(data.getCargo());
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

    @Override
    public String obtenerCorreo(String depto) {
        String correo = solicitudVehiculoServices.obtenerCorreoJefeDepto(depto);
        return correo;
    }

    @Override
    public String obtenerCorreoNombre(String id) {
        String datos = solicitudVehiculoServices.obtenerSolicitanteDatos(id);
        return datos;
    }

    @Override
    public String obtenerCorreoNombreRol(String rol) {
        String datos  = solicitudVehiculoServices.obtenerEmailNombreRol(rol);
        return datos;
    }

    @Override
    public SolicitudVehiculo ConsinVale(UUID id) {
        SolicitudVehiculo buscar= solicitudVehiculoServices.findByCodigoSolicitudVehiculo(id);
        return buscar;
    }
}
