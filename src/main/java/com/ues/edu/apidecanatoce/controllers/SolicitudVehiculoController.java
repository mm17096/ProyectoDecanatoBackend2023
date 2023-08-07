package com.ues.edu.apidecanatoce.controllers;

import com.ues.edu.apidecanatoce.dtos.SolicitudVehiculoDTO;
import com.ues.edu.apidecanatoce.entities.*;
import com.ues.edu.apidecanatoce.services.ISolicitudVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitudvehiculo")
public class SolicitudVehiculoController {

    private final ISolicitudVehiculoService servicioSolicitudVehiculo;
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
    public ResponseEntity<GenericResponse<SolicitudVehiculoDTO>> guardarSolicitud(@RequestBody SolicitudVehiculoDTO solicitudDTO) {
        HttpStatus http;
        GenericResponse<SolicitudVehiculoDTO> resp = new GenericResponse<SolicitudVehiculoDTO>(0,
                "ERROR DE ALMACENAMIENTO DE LA CONSULTA", solicitudDTO);
        Optional<SolicitudVehiculoDTO> opt = Optional.ofNullable(solicitudDTO);

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
            this.motorista.setCodigoEmpleado(solicitudDTO.getCodigoMotorista());
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


}
