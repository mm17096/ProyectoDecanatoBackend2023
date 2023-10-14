package com.ues.edu.apidecanatoce.servicesImpl.entradaSalida;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.solicitudes.EmpleadosCorreosSolicitudesDto;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaDto;
import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.EntradasalidaPeticionDto;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.LogSoliVeDTO;
import com.ues.edu.apidecanatoce.entities.entradaSalida.Entrada_Salidas;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.LogSolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.usuario.Usuario;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.IAsignacionValeRepository;
import com.ues.edu.apidecanatoce.repositorys.entradaSalida.EntradasalidaRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ILogSoliVeRepository;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ISolicitudVehiculoRepository;
import com.ues.edu.apidecanatoce.repositorys.usuario.IUsuarioRepository;
import com.ues.edu.apidecanatoce.services.Ientradasalidaservice;
import com.ues.edu.apidecanatoce.servicesImpl.asignacionvale.AsignacionValeServiceImpl;
import com.ues.edu.apidecanatoce.servicesImpl.solicitudVale.SolicitudValeServiceImpl;
import com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.SolicitudVehiculoServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EntradasalidaImpl implements Ientradasalidaservice
{
    private final IAsignacionValeRepository asignacionValeRepository;
    private final EntradasalidaRepository entradasalidaRepository;
    private  final ISolicitudVehiculoRepository solicitudVehiculorepository;
    private  final AsignacionValeServiceImpl asignacionValeServiceImpl;
    private final SolicitudValeServiceImpl solicitudValeServiceImpl;
    private final SolicitudVehiculoServicesImpl solicitudvehiculoServices;
    private final ILogSoliVeRepository logSoliVeRepository;
    private final IUsuarioRepository usuarioRepository;




    @Override
    public EntradasalidaPeticionDto registrar(EntradasalidaDto data) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LogSoliVeDTO logSoliVe = new LogSoliVeDTO();
        String nombreUsuario = obtenerUsuarioAutenticado(authentication);
        String userName = authentication.getName();
        Optional<Usuario> user = usuarioRepository.findByNombre(userName);
        String nombreCargo = "";


        if (user.isPresent()){
            Usuario usuario = user.get();
            nombreCargo = usuario.getEmpleado().getCargo().getNombreCargo();
        }else{
            System.out.println("USUARIO VACIO");
        }

        if(data.getEstado()==2){
            SolicitudVehiculo solicitud1=solicitudvehiculoServices.ConsinVale(data.getSolicitudvehiculo());
            if (solicitud1.isTieneVale()==true){
                //entradasalida--- solicitudvehiculo-- solicitudvale
                SolicitudVale solicitudVale= solicitudValeServiceImpl.codigosolicitudvehiculo(data.getSolicitudvehiculo());
                asignacionValeServiceImpl.actualizarEstadoEntradaSolicitud(solicitudVale.getIdSolicitudVale(),2);

            }else {

                logSoliVe.setActividad("Misión sin vales finalizada");
                logSoliVe.setEstadoLogSolive(7);
                logSoliVe.setFechaLogSoliVe(LocalDateTime.now());
                logSoliVe.setUsuario(nombreUsuario);
                logSoliVe.setCargo(nombreCargo);
                logSoliVe.setSoliVe(data.getSolicitudvehiculo());
                logSolicitudVehiculo(logSoliVe);
                asignacionValeServiceImpl.actualizarEstadoSolicitudVehiculo(data.getSolicitudvehiculo(),7);
            }
        }else if(data.getEstado()==1){
            SolicitudVehiculo solicitud=solicitudvehiculoServices.ConsinVale(data.getSolicitudvehiculo());
            if (solicitud.isTieneVale()==true){
                SolicitudVale solicitudVale= solicitudValeServiceImpl.codigosolicitudvehiculo(data.getSolicitudvehiculo());
                asignacionValeServiceImpl.actualizarEstadoEntradaSolicitud(solicitudVale.getIdSolicitudVale(),1);
            }
        }
        return entradasalidaRepository.save(data.toEntityComplete(solicitudVehiculorepository)).toDTO();
    }

    @Override
    public EntradasalidaPeticionDto leerPorId(UUID id) {
        Entrada_Salidas entradaSalidas = entradasalidaRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra ningun dato registrado del que desea actualizar"));
        return entradaSalidas.toDTO();
    }

    @Override
    public Page<EntradasalidaPeticionDto> listar(Pageable pageable) {
        Page<Entrada_Salidas> entradaSalidas = entradasalidaRepository.findAll(pageable);
        return entradaSalidas.map(Entrada_Salidas::toDTO);
    }

    @Override
    public List<EntradasalidaDto> listarSinPagina() {
        return null;
    }


    @Override
    public EntradasalidaDto actualizar(UUID id, EntradasalidaDto data) {
        return null;
    }
    @Override
    public EntradasalidaDto eliminar(UUID id) {

        return null;
    }

    @Override
    public Entrada_Salidas listaEstado(int estadi, UUID id) {
        Entrada_Salidas buscar= entradasalidaRepository.findByEstadoAndSolicitudvehiculo_CodigoSolicitudVehiculo(estadi, id);
        return buscar;
    }


    @Override
    public LogSoliVeDTO logSolicitudVehiculo(LogSoliVeDTO data) {


        SolicitudVehiculo soliVeh = this.solicitudVehiculorepository.findById(data.getSoliVe()).orElseThrow(
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

    @Override
    public List<EmpleadosCorreosSolicitudesDto> correosFinanciero() throws IOException {
        return asignacionValeRepository.correosFinanciero();
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
}
