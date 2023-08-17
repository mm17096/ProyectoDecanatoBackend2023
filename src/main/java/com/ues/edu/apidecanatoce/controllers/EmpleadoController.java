package com.ues.edu.apidecanatoce.controllers;

import com.ues.edu.apidecanatoce.dtos.EmpleadoTablaDTO;
import com.ues.edu.apidecanatoce.entities.Empleado;
import com.ues.edu.apidecanatoce.repositorys.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.services.IEmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    private final IEmpleadoService empleadoService;

    private final IEmpleadoRepository empleadoRepository;

    public EmpleadoController(IEmpleadoService empleadoService, IEmpleadoRepository empleadoRepository) {
        this.empleadoService = empleadoService;
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> mostrarEmpleados() {
        List<Empleado> empleado = this.empleadoService.listar();
        return new ResponseEntity<List<Empleado>>(empleado, HttpStatus.OK);
    }

    @GetMapping("/tabla")
    public ResponseEntity<List<EmpleadoTablaDTO>> listaEmpleadosTabla(){
        List<EmpleadoTablaDTO> listaempleadoDTO = new ArrayList<>();
        List<Empleado> empleados = this.empleadoService.listar();
        for (Empleado empleado : empleados) {
            EmpleadoTablaDTO empleadoTablaDTO = new EmpleadoTablaDTO();
            empleadoTablaDTO.setDui(empleado.getDui());
            empleadoTablaDTO.setNombre(empleado.getNombre());
            empleadoTablaDTO.setApellido(empleado.getApellido());
            empleadoTablaDTO.setTelefono(empleado.getTelefono());
            empleadoTablaDTO.setLicencia(empleado.getLicencia());
            empleadoTablaDTO.setTipo_licencia(empleado.getTipo_licencia());
            empleadoTablaDTO.setFecha_licencia(empleado.getFecha_licencia());
            //Estado
            if(empleado.getEstado() == 7){
                empleadoTablaDTO.setEstado("Activo");
            }else{
                empleadoTablaDTO.setEstado("Inactivo");
            }
            //Jefe
            if(empleado.isJefe()){
                empleadoTablaDTO.setJefe("SI");
            }else{
                empleadoTablaDTO.setJefe("NO");
            }
            empleadoTablaDTO.setCorreo(empleado.getCorreo());
            empleadoTablaDTO.setNombrefoto(empleado.getNombrefoto());
            empleadoTablaDTO.setUrlfoto(empleado.getUrlfoto());
            empleadoTablaDTO.setCargo(empleado.getCargo().getNombreCargo());
            empleadoTablaDTO.setDepartamento(empleado.getDepartamento().getNombre());

            listaempleadoDTO.add(empleadoTablaDTO);
        }
        return new ResponseEntity<List<EmpleadoTablaDTO>>(listaempleadoDTO, HttpStatus.OK);
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
