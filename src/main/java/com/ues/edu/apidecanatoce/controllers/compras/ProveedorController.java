package com.ues.edu.apidecanatoce.controllers.compras;

import com.ues.edu.apidecanatoce.dtos.compras.ProveedorDto;
import com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.services.compras.IProveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    private final IProveedorService proveedorService;

    @PostMapping(value = "/insertar")
    public ResponseEntity<ProveedorDto> registrar(@Valid @RequestBody ProveedorDto proveedor) {
        return ResponseEntity.ok(proveedorService.registrar(proveedor));
    }

    @GetMapping("/listasinpagina")
    public ResponseEntity<List<ProveedorDto>> listarSinPagina() {
        List<ProveedorDto> proveedor = proveedorService.listarSinPagina();
        return ResponseEntity.ok(proveedor);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<ProveedorDto> leerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(proveedorService.leerPorId(id));
    }

    @GetMapping("/lista")
    public ResponseEntity<Page<ProveedorDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(proveedorService.listar(pageable));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ProveedorDto> actualizar(
            @PathVariable UUID id, @Valid @RequestBody ProveedorDto proveedor) {
        return ResponseEntity.ok(proveedorService.actualizar(id, proveedor));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ProveedorDto> eliminar(@PathVariable UUID id) {
        return ResponseEntity.ok(proveedorService.eliminar(id));
    }


}
