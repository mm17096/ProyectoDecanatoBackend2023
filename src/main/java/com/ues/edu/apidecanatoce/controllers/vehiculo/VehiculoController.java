package com.ues.edu.apidecanatoce.controllers.vehiculo;

import com.ues.edu.apidecanatoce.dtos.compras.ProveedorDto;
import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import com.ues.edu.apidecanatoce.services.vehiculo.IVehiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/insertar")
    public ResponseEntity<VehiculoDto> registrar(@Valid @RequestBody VehiculoDto vehiculoDto) {
        return ResponseEntity.ok(vehiculoService.registrar(vehiculoDto));
    }

    @PutMapping("/editar")
    public ResponseEntity<VehiculoDto> actualizar(@Valid @RequestBody VehiculoDto vehiculo) {
        return ResponseEntity.ok(vehiculoService.actualizar(vehiculo));
    }





}
