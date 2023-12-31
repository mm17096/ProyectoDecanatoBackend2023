package com.ues.edu.apidecanatoce.controllers.empleado;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoDto;
import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoPeticionDto;

import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import com.ues.edu.apidecanatoce.repositorys.empleado.IEmpleadoRepository;
import com.ues.edu.apidecanatoce.services.PathService;
import com.ues.edu.apidecanatoce.servicesImpl.empleado.EmpleadoServiceImpl;
import com.ues.edu.apidecanatoce.servicesImpl.usuario.UsuarioServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/empleado")
@CrossOrigin(origins = "*")
//@PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
public class EmpleadoController {

    private final EmpleadoServiceImpl empleadoServiceimpl;
    private final PathService pathService;
    private final HttpServletRequest request;
    private final IEmpleadoRepository empleadoRepository;

    public EmpleadoController(EmpleadoServiceImpl empleadoServiceimpl, PathService pathService, HttpServletRequest request, IEmpleadoRepository empleadoRepository) {
        this.empleadoServiceimpl = empleadoServiceimpl;
        this.pathService = pathService;
        this.request = request;
        this.empleadoRepository = empleadoRepository;
    }

/////// Metodos reestructurados con EMpleadosServiceImplement /////////


    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
    public ResponseEntity<EmpleadoPeticionDto> leerPorID(@PathVariable UUID uuid) {
        return ResponseEntity.ok(empleadoServiceimpl.leerPorId(uuid));
    }

    @GetMapping("/motoristas")
    @PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
    public ResponseEntity<List<Empleado>> Motoristas(@RequestParam String fechaSalida, @RequestParam String fechaEntrada) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaSalidaDate;
        Date fechaEntradaDate;
        try {
            fechaSalidaDate = dateFormat.parse(fechaSalida);
            fechaEntradaDate = dateFormat.parse(fechaEntrada);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(empleadoRepository.listaMotoristas(fechaSalidaDate,fechaEntradaDate));

    }

    @GetMapping("/lista")
    @PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
    public ResponseEntity<Page<EmpleadoPeticionDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(empleadoServiceimpl.listar(pageable));
    }

    /////// endpoint para editar sin imagen ////////
    @PostMapping("/insertar")
    @PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
    public EmpleadoPeticionDto InsertarEmpleado(@RequestBody EmpleadoDto empleado) {
        return this.empleadoServiceimpl.registrar(empleado);
    }

    /////// endpoint para insertar con imagen ////////
    @PostMapping("/insertarconImagen")
    @PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
    public EmpleadoPeticionDto InsertarEmpleadoconImagen(@PathVariable MultipartFile imagen, @Valid @RequestParam("empleado") String empleadoJson) {
        try {
            // Crear el objetmapper y agregar el mapeo de fechas
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            EmpleadoDto empleado = objectMapper.readValue(empleadoJson, EmpleadoDto.class);

            // Guardar la nueva imagen en la carpeta del proyecto
            String filename = pathService.generateFileName(imagen);
            pathService.storeFile(imagen, filename);
            Path destinationFile = pathService.generatePath(filename);
            Files.write(destinationFile, imagen.getBytes());

            // Guardar la URL de la imagen en el campo urlfoto y el nombre en nombrefoto del vehículo
            empleado.setNombrefoto(generateUrlImage(filename));
            empleado.setUrlfoto(destinationFile.toString());

            // Guardar los cambios del vehículo en la base de datos
            return this.empleadoServiceimpl.registrar(empleado);
        } catch (IOException e) {
            // Manejar la excepción en caso de que ocurra un error al procesar la imagen
            e.printStackTrace();
            String mensajeError = "Ocurrió un error al procesar la imagen.";
            return null;// Devuelve una respuesta de error
        }
    }

    /////// endpoint para editar sin imagen ////////
    @PutMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
    public ResponseEntity<EmpleadoPeticionDto> actualizar(
            @PathVariable UUID id, @Valid @RequestBody EmpleadoDto empleado) {
        return ResponseEntity.ok(empleadoServiceimpl.actualizar(id, empleado));
    }

    /////// endpoint para editar con imagen ////////
    @PutMapping("/editarconImagen/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
    public ResponseEntity<EmpleadoPeticionDto> actualizarconImagen(
            @PathVariable UUID id, @PathVariable MultipartFile imagen, @Valid @RequestParam("empleado") String empleadoJson) {
        try {
            // Crear el objetmapper y agregar el mapeo de fechas
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            EmpleadoDto empleado = objectMapper.readValue(empleadoJson, EmpleadoDto.class);

            // Si la URL de la imagen anterior no está vacía, intenta eliminarla
            if (empleado != null && empleado.getUrlfoto() != null) {
                try {
                    Files.delete(Path.of(empleado.getUrlfoto()));
                    System.out.println("Imagen eliminada exitosamente.");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Error al eliminar la imagen.");
                }
            }

            // Guardar la nueva imagen en la carpeta del proyecto
            String filename = pathService.generateFileName(imagen);
            pathService.storeFile(imagen, filename);
            Path destinationFile = pathService.generatePath(filename);
            Files.write(destinationFile, imagen.getBytes());

            // Guardar la URL de la imagen en el campo urlfoto y el nombre en nombrefoto del vehículo
            empleado.setNombrefoto(generateUrlImage(filename));
            empleado.setUrlfoto(destinationFile.toString());

            // Guardar los cambios del vehículo en la base de datos
            return ResponseEntity.ok(empleadoServiceimpl.actualizar(id, empleado));
        } catch (IOException e) {
            // Manejar la excepción en caso de que ocurra un error al procesar la imagen
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Devuelve una respuesta de error
        }
    }

///////// metodos para la imagen /////////

    ///////// para generar la url //////////
    public String generateUrlImage(String imageName) {
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        return host + "/api/empleado/imagen/" + imageName;
    }

    //////// para mostrar la imagen /////////
    @GetMapping("/imagen/{nombrefoto}")
    public ResponseEntity<byte[]> getImagen(@PathVariable("nombrefoto") String filename) {
        byte[] image = new byte[0];
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
}
