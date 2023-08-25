package com.ues.edu.apidecanatoce.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ues.edu.apidecanatoce.dtos.EmpleadoTablaDTO;
import com.ues.edu.apidecanatoce.entities.Empleado;
import com.ues.edu.apidecanatoce.entities.GenericResponse;
import com.ues.edu.apidecanatoce.repositorys.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.services.IEmpleadoService;
import com.ues.edu.apidecanatoce.services.PathService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    private final IEmpleadoService empleadoService;
    private final PathService pathService;
    private final HttpServletRequest request;

    public EmpleadoController(IEmpleadoService empleadoService, IEmpleadoRepository empleadoRepository, PathService pathService, HttpServletRequest request) {
        this.empleadoService = empleadoService;
        this.pathService = pathService;
        this.request = request;

    }

    @GetMapping
    public ResponseEntity<List<Empleado>> mostrarEmpleados() {
        List<Empleado> empleado = this.empleadoService.listar();
        return new ResponseEntity<List<Empleado>>(empleado, HttpStatus.OK);
    }


    @GetMapping("/tabla")
    public ResponseEntity<List<EmpleadoTablaDTO>> listaEmpleadosTabla() {
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
            empleadoTablaDTO.setEstado(empleado.getEstado());
            empleadoTablaDTO.setJefe(empleado.isJefe());
            empleadoTablaDTO.setCorreo(empleado.getCorreo());
            if (empleado.getNombrefoto() != null) {
                empleadoTablaDTO.setNombrefoto(generateUrlImage(empleado.getNombrefoto()));
                empleadoTablaDTO.setUrlfoto(empleado.getUrlfoto());
            } else {
                empleadoTablaDTO.setNombrefoto("");
                empleadoTablaDTO.setUrlfoto("");
            }
            empleadoTablaDTO.setCargo(empleado.getCargo());
            empleadoTablaDTO.setDepartamento(empleado.getDepartamento());

            listaempleadoDTO.add(empleadoTablaDTO);
        }
        return new ResponseEntity<List<EmpleadoTablaDTO>>(listaempleadoDTO, HttpStatus.OK);
    }

    public String generateUrlImage(String imageName) {
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        return host + "/empleado/imagen/" + imageName;
    }

    @GetMapping("/imagen/{nombrefoto}")
    public ResponseEntity<byte[]> getImagen(@PathVariable("nombrefoto") String filename) {
        byte[] image = new byte[0];
        //String path = "C:\\Images\\";
        String path = "./uploads";
        try {
            Path fileName = Paths.get(path, filename);
            image = Files.readAllBytes(fileName);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
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

    //Editar datos existentes
    public Empleado modificarEmpleado(@RequestBody Empleado vehículo) {
        return this.empleadoService.registrar(vehículo);
    }

    @PutMapping("/modificarconImagen")
    public ResponseEntity<GenericResponse<Empleado>> EditarEmpleadoConImagen(@RequestParam("imagen") MultipartFile imagen, @RequestParam("empleado") String empleadoJson) {
        GenericResponse<Empleado> resp;
        Empleado empleadoResponse;
        try {
            // Crear el objetmapper y agregar el mapeo de fechas
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            Empleado empleado = objectMapper.readValue(empleadoJson, Empleado.class);

            if (empleado != null && empleado.getUrlfoto() != null) {
                Files.delete(Path.of(empleado.getUrlfoto()));
            }

            // Guardar la nueva imagen en la carpeta del proyecto
            String filename = pathService.generateFileName(imagen);
            pathService.storeFile(imagen, filename);
            Path destinationFile = pathService.generatePath(filename);
            Files.write(destinationFile, imagen.getBytes());

            // Guardar la URL de la imagen en el campo urlfoto y el nombre en nombrefoto del vehículo
            empleado.setNombrefoto(filename);
            empleado.setUrlfoto(destinationFile.toString());

            // Guardar los cambios del vehículo en la base de datos
            empleadoResponse = modificarEmpleado(empleado);

            resp = new GenericResponse<Empleado>(1, "Vehiculo modificado con exito", empleadoResponse);
            return new ResponseEntity<GenericResponse<Empleado>>(resp, HttpStatus.OK);
        } catch (IOException e) {
            // Manejar la excepción en caso de que ocurra un error al procesar la imagen
            e.printStackTrace();
            return null; // O devuelve un mensaje de error o una respuesta adecuada
        }
    }

    @PutMapping("/modificarsinImagen")
    public ResponseEntity<GenericResponse<Empleado>> EditarEmpleado(@RequestBody Empleado empleado) {
        GenericResponse<Empleado> resp;
        Empleado empleadoResponse;

        Optional<Empleado> opt = Optional.ofNullable(this.empleadoService.leerPorDUI(empleado.getDui()));
        Empleado empleadoMod = opt.get();

        //se la cambiamos porque viene con la url para mostrar
        empleado.setNombrefoto(empleadoMod.getNombrefoto());

        empleadoResponse = modificarEmpleado(empleado);
        resp = new GenericResponse<Empleado>(1, "Empleado modificado con exito", empleadoResponse);
        return new ResponseEntity<GenericResponse<Empleado>>(resp, HttpStatus.OK);
    }

}
