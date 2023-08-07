package com.ues.edu.apidecanatoce.controllers;

import com.ues.edu.apidecanatoce.entities.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.services.ISolicitudVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/solicitudvehiculo")
public class SolicitudVehiculoController {

    private final ISolicitudVehiculoService servicioSolicitudVehiculo;

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

}
