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
public class VehiculoController {

    private final IVehiculoService vehiculoService;

    @GetMapping("/lista")
    public ResponseEntity<Page<VehiculoDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(vehiculoService.listar(pageable));
    }

    @GetMapping("/listasinpagina")
    public ResponseEntity<List<VehiculoDto>> listarVehiculos() {
        List<VehiculoDto> vehiculo = vehiculoService.listarSinPagina();
        return ResponseEntity.ok(vehiculo);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<VehiculoDto> leerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(vehiculoService.leerPorId(id));
    }

    @GetMapping("/listasinpagina/{codigoplaca}")
    public ResponseEntity<List<VehiculoDto>> BuscarPorplaca(@PathVariable String codigoplaca){
        return ResponseEntity.ok(vehiculoService.listarPorPlaca(codigoplaca));
    }

    @GetMapping("/clase/{claseName}")
    public ResponseEntity<List<VehiculoDto>> listadoPorClase(@PathVariable String claseName){
        return ResponseEntity.ok(vehiculoService.listarPorClase(claseName));
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

    @PostMapping(value = "/insertar", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MensajeRecord> registrar(
            @RequestPart(name = "imagen", required = false) MultipartFile imagen,
            @Valid @RequestPart(name = "vehiculo") VehiculoDto vehiculoDto) {
        return ResponseEntity.ok(vehiculoService.registrar(imagen, vehiculoDto));
    }

    @PutMapping(value = "/editar", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MensajeRecord> actualizar(
            @RequestPart(name = "imagen", required = false) MultipartFile imagen,
            @Valid @RequestPart(name = "vehiculo") VehiculoDto vehiculo) {
        return ResponseEntity.ok(vehiculoService.actualizar(imagen, vehiculo));
    }

}
