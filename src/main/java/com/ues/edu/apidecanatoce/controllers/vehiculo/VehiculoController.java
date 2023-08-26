package com.ues.edu.apidecanatoce.controllers.vehiculo;


import com.ues.edu.apidecanatoce.dtos.MensajeRecord;
import com.ues.edu.apidecanatoce.dtos.compras.ProveedorDto;
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
