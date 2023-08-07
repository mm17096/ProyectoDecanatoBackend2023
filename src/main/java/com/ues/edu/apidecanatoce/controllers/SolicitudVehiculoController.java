package com.ues.edu.apidecanatoce.controllers;

import com.ues.edu.apidecanatoce.entities.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.services.ISolicitudVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
