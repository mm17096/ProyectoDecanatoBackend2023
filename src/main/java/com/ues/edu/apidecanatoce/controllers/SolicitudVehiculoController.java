package com.ues.edu.apidecanatoce.controllers;

import com.ues.edu.apidecanatoce.dtos.SolicitudVehiculoDTORequest;
import com.ues.edu.apidecanatoce.dtos.SolicitudVehiculoDTOResponse;
import com.ues.edu.apidecanatoce.entities.*;
import com.ues.edu.apidecanatoce.repositorys.ConfigSoliVeRepository;
import com.ues.edu.apidecanatoce.repositorys.EstadosRepository;
import com.ues.edu.apidecanatoce.services.ISolicitudVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitudvehiculo")
public class SolicitudVehiculoController {

    @Autowired
    private ConfigSoliVeRepository configuracionRepository;
    private final ISolicitudVehiculoService servicioSolicitudVehiculo;
    @Autowired
    private EstadosRepository estadosRepository;
    private SolicitudVehiculo solicitudVehiculo;
    private Empleado motorista;
    private Usuario usuario;
    private Vehiculo vehiculo;

    @Autowired
    public SolicitudVehiculoController(ISolicitudVehiculoService servicioSolicitudVehiculo) {
        this.servicioSolicitudVehiculo = servicioSolicitudVehiculo;
    }

    // listar solicitudes
    @GetMapping("/lista")
    public ResponseEntity<List<SolicitudVehiculo>> obtenerSolicitudes() throws IOException {
        List<SolicitudVehiculo> vehiculos = this.servicioSolicitudVehiculo.listar();
        return new ResponseEntity<>(vehiculos, HttpStatus.OK);
    }

    // listar solicitudes vehiculo por DTO
    @GetMapping("/listadto")
    public ResponseEntity<List<SolicitudVehiculoDTOResponse>> listaSolicitudesDTO() throws IOException {
        List<SolicitudVehiculo> soliVehiculos = this.servicioSolicitudVehiculo.listar();
        List<SolicitudVehiculoDTOResponse> soliVehiculosDTOResp = new ArrayList<>();
        List<Estados> estados = estadosRepository.findAll();

        for (SolicitudVehiculo soliVe: soliVehiculos){
            SolicitudVehiculoDTOResponse soliVeDTOResp = new SolicitudVehiculoDTOResponse();
            soliVeDTOResp.setCodigoSolicitudVehiculo(soliVe.getCodigoSolicitudVehiculo());
            soliVeDTOResp.setFechaSolicitud(soliVe.getFechaSolicitud());
            soliVeDTOResp.setFechaSalida(soliVe.getFechaSalida());
            soliVeDTOResp.setUnidadSolicitante(soliVe.getUnidadSolicitante());
            soliVeDTOResp.setVehiculo(soliVe.getVehiculo());
            soliVeDTOResp.setObjetivoMision(soliVe.getObjetivoMision());
            soliVeDTOResp.setLugarMision(soliVe.getLugarMision());
            soliVeDTOResp.setDireccion(soliVe.getDireccion());
            soliVeDTOResp.setHoraEntrada(soliVe.getHoraEntrada());
            soliVeDTOResp.setHoraSalida(soliVe.getHoraSalida());
            soliVeDTOResp.setCantidadPersonas(soliVe.getCantidadPersonas());
            soliVeDTOResp.setListaPasajeros(soliVe.getListaPasajeros());
            soliVeDTOResp.setSolicitante(soliVe.getUsuario());
            soliVeDTOResp.setNombreJefeDepto(soliVe.getJefeDepto());
            soliVeDTOResp.setFechaEntrada(soliVe.getFechaEntrada());

            for (Estados estado: estados){
                if (soliVe.getEstado() == estado.getCodigoEstado()){
                    soliVeDTOResp.setEstado(estado.getNombreEstado());
                }
            }

            soliVeDTOResp.setMotorista(soliVe.getMotorista());
            soliVeDTOResp.setListDocumentos(soliVe.getListDocumentos());

            soliVehiculosDTOResp.add(soliVeDTOResp);
        }
        return new ResponseEntity<List<SolicitudVehiculoDTOResponse>>(soliVehiculosDTOResp, HttpStatus.OK);
    }

    @GetMapping("/config")
    public ResponseEntity<List<ConfigSoliVe>> obtenerConfiguracion(){
        List<ConfigSoliVe> configSoliVes = this.configuracionRepository.findAll();
        return new ResponseEntity<>(configSoliVes, HttpStatus.OK);
    }


    // metodo para filtrar las solicitudes segun estado
    @GetMapping("/lista/{estado}")
    public ResponseEntity<List<SolicitudVehiculo>> obtenerSolicitudesPorEstado(@PathVariable("estado") Integer estado) throws IOException {
        List<SolicitudVehiculo> vehiculos = this.servicioSolicitudVehiculo.listarPorEstado(estado);
        return new ResponseEntity<>(vehiculos, HttpStatus.OK);
    }

    @PostMapping(value = "/insertar")
    public SolicitudVehiculo guardarSolicitud(@RequestBody SolicitudVehiculo solicitudVehiculo){
        System.out.println("objeto: "+solicitudVehiculo);
        return this.servicioSolicitudVehiculo.registrar(solicitudVehiculo);
    }

    @PostMapping("/insertardto")
    public ResponseEntity<GenericResponse<SolicitudVehiculoDTORequest>> guardarSolicitudDto(@RequestBody SolicitudVehiculoDTORequest solicitudDTO) {
        HttpStatus http;
        GenericResponse<SolicitudVehiculoDTORequest> resp = new GenericResponse<SolicitudVehiculoDTORequest>(0,
                "ERROR DE ALMACENAMIENTO DE LA CONSULTA", solicitudDTO);
        Optional<SolicitudVehiculoDTORequest> opt = Optional.ofNullable(solicitudDTO);

        if (opt.isPresent()){
            this.solicitudVehiculo = new SolicitudVehiculo();
            this.solicitudVehiculo.setFechaSolicitud(solicitudDTO.getFechaSolicitud());
            this.solicitudVehiculo.setObjetivoMision(solicitudDTO.getObjetivoMision());
            this.solicitudVehiculo.setLugarMision(solicitudDTO.getLugarMision());
            this.solicitudVehiculo.setFechaSalida(solicitudDTO.getFechaSalida());
            this.solicitudVehiculo.setFechaEntrada(solicitudDTO.getFechaEntrada());
            this.solicitudVehiculo.setHoraEntrada(solicitudDTO.getHoraEntrada());
            this.solicitudVehiculo.setHoraSalida(solicitudDTO.getHoraSalida());
            this.solicitudVehiculo.setCantidadPersonas(solicitudDTO.getCantidadPersonas());
            this.solicitudVehiculo.setEstado(solicitudDTO.getEstado());

            this.usuario = new Usuario();
            this.usuario.setCodigoUsuario(solicitudDTO.getCodigoUsuario()); // Solo seteamos el ID
            solicitudVehiculo.setUsuario(this.usuario); // Establecemos la relación con el usuario

            this.motorista = new Empleado();
            this.motorista.setDui(solicitudDTO.getCodigoMotorista());
            solicitudVehiculo.setMotorista(this.motorista);

            this.vehiculo = new Vehiculo();
            this.vehiculo.setCodigoVehiculo(solicitudDTO.getCodigoVehiculo());
            solicitudVehiculo.setVehiculo(this.vehiculo);

            try {
                this.servicioSolicitudVehiculo.registrar(solicitudVehiculo);
                resp.setCode(1);
                resp.setMessage("Exito");
                http = HttpStatus.OK;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Message: "+e.getMessage());
                http = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            resp.setCode(0);
            resp.setMessage("Fallo - No se encontro solicitud");
            http = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(resp,http);
    }

    @PutMapping("/editar")
    public ResponseEntity<GenericResponse<SolicitudVehiculo>> editarSolicitudVehiculo(
            @RequestBody SolicitudVehiculo solicitudVehiculo
    ){
        System.out.println("datos:"+solicitudVehiculo);
        Optional<SolicitudVehiculo> optionalSolicitudVehiculo =
                Optional.ofNullable(this.servicioSolicitudVehiculo.leerPorId(solicitudVehiculo
                        .getCodigoSolicitudVehiculo()));
        GenericResponse<SolicitudVehiculo> response;
        SolicitudVehiculo solicitudVehiculoResp;
        if (optionalSolicitudVehiculo.isPresent()){

            // Convertir IDs en objetos antes de guardar la solicitud
            Usuario usuario = new Usuario();
            usuario.setCodigoUsuario(solicitudVehiculo.getUsuario().getCodigoUsuario());

            Empleado motorista = new Empleado();
            motorista.setDui(solicitudVehiculo.getMotorista().getDui());

            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setCodigoVehiculo(solicitudVehiculo.getVehiculo().getCodigoVehiculo());

            solicitudVehiculo.setUsuario(usuario);
            solicitudVehiculo.setMotorista(motorista);
            solicitudVehiculo.setVehiculo(vehiculo);

            solicitudVehiculoResp = guardarSolicitud(solicitudVehiculo);
            response = new GenericResponse<>(1, "Solicitud Guardada con exito", solicitudVehiculoResp);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response = new GenericResponse<>(0, "Solicitud en edicion no guardada", solicitudVehiculo);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
