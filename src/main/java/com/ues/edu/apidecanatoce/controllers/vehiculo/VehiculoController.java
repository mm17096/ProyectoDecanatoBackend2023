package com.ues.edu.apidecanatoce.controllers.vehiculo;
import com.ues.edu.apidecanatoce.dtos.MensajeRecord;
import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import com.ues.edu.apidecanatoce.services.vehiculo.IVehiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vehiculo")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
public class VehiculoController {

    private final IVehiculoService vehiculoService;

    @GetMapping("/lista")
    @PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
    public ResponseEntity<Page<VehiculoDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(vehiculoService.listar(pageable));
    }

    @GetMapping("/listasinpagina")
    @PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
    public ResponseEntity<List<VehiculoDto>> listarVehiculos() {
        List<VehiculoDto> vehiculo = vehiculoService.listarSinPagina();
        return ResponseEntity.ok(vehiculo);
    }

    @GetMapping("/lista/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
    public ResponseEntity<VehiculoDto> leerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(vehiculoService.leerPorId(id));
    }

    @GetMapping("/listasinpagina/{codigoplaca}")
    @PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
    public ResponseEntity<List<VehiculoDto>> BuscarPorplaca(@PathVariable String codigoplaca){
        return ResponseEntity.ok(vehiculoService.listarPorPlaca(codigoplaca));
    }

    @GetMapping("/clase")
    @PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
    public ResponseEntity<List<String>> listadoPorClase(){
        return ResponseEntity.ok(vehiculoService.listarPorClase());
    }

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

    @GetMapping("/disponibilidad")
    @PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
    public ResponseEntity<List<VehiculoDto>> listadoPorDisponibilidad(
            @RequestParam String claseName, @RequestParam String fechaSalida, @RequestParam String fechaEntrada) {
        return ResponseEntity.ok(vehiculoService.listarPorDisponibilidad(claseName, fechaSalida, fechaEntrada));
    }

    @PostMapping(value = "/insertar", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
    public ResponseEntity<MensajeRecord> registrar(
            @RequestPart(name = "imagen", required = false) MultipartFile imagen,
            @Valid @RequestPart(name = "vehiculo") VehiculoDto vehiculoDto) {
        return ResponseEntity.ok(vehiculoService.registrar(imagen, vehiculoDto));
    }

    @PutMapping(value = "/editar", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('ADMIN','SECR_DECANATO','JEFE_DEPTO','VIGILANTE','DECANO','ASIS_FINANCIERO','USER','JEFE_FINANACIERO')")
    public ResponseEntity<MensajeRecord> actualizar(
            @RequestPart(name = "imagen", required = false) MultipartFile imagen,
            @Valid @RequestPart(name = "vehiculo") VehiculoDto vehiculo) {
        return ResponseEntity.ok(vehiculoService.actualizar(imagen, vehiculo));
    }

}
