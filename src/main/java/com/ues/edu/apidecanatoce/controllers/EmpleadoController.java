package com.ues.edu.apidecanatoce.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ues.edu.apidecanatoce.dtos.EmpleadoTablaDTO;
import com.ues.edu.apidecanatoce.entities.Empleado;
import com.ues.edu.apidecanatoce.repositorys.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.services.IEmpleadoService;
import com.ues.edu.apidecanatoce.services.PathService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    private final IEmpleadoService empleadoService;
    private final IEmpleadoRepository empleadoRepository;
    private final PathService pathService;

    public EmpleadoController(IEmpleadoService empleadoService, IEmpleadoRepository empleadoRepository, PathService pathService) {
        this.empleadoService = empleadoService;
        this.empleadoRepository = empleadoRepository;
        this.pathService = pathService;
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
    public Empleado guardarEmpleado(@RequestParam("imagen") MultipartFile imagen, @RequestParam("empleado") String empleadoJson) {
        try {
            // Crear el objetmapper y agregar el mapeo de fechas
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            Empleado empleado = objectMapper.readValue(empleadoJson, Empleado.class);

            // Guardar la imagen en la carpeta del proyecto
            String filename = pathService.generateFileName(imagen);
            pathService.storeFile(imagen, filename);
            Path destinationFile = pathService.generatePath(filename);
            Files.write(destinationFile, imagen.getBytes());

            // Guardar la URL de la imagen en el campo urlfoto y el nombre en nombrefoto del vehículo
            empleado.setNombrefoto(filename);
            empleado.setUrlfoto(destinationFile.toString());

            // Guardar el empleado en la base de datos
            return this.empleadoService.registrar(empleado);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
