package com.ues.edu.apidecanatoce.controllers;

import com.ues.edu.apidecanatoce.entities.Empleado;
import com.ues.edu.apidecanatoce.services.IEmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    private final IEmpleadoService empleadoService;

    public EmpleadoController(IEmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> mostrarEmpleados() {
        List<Empleado> empleado = this.empleadoService.listar();
        return new ResponseEntity<List<Empleado>>(empleado, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> empleadoById(@PathVariable("id") Integer ID) {
        Empleado vehículo = this.empleadoService.leerPorId(ID);
        return new ResponseEntity<Empleado>(vehículo, HttpStatus.OK);
    }

    @PostMapping("/insertar")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
        return this.empleadoService.registrar(empleado);
    }
}
