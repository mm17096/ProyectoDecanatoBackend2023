package com.ues.edu.apidecanatoce.controllers.compras;

import com.ues.edu.apidecanatoce.dtos.compras.ValeDependeDto;
import com.ues.edu.apidecanatoce.dtos.compras.ValeDto;
import com.ues.edu.apidecanatoce.services.compras.IValeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vale")
public class ValeController {

    private final IValeService valeService;

    @PostMapping(value = "/insertar")
    public ResponseEntity<ValeDependeDto> registrar(@Valid @RequestBody ValeDto vale) {
        return ResponseEntity.ok(valeService.registrar(vale));
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<ValeDependeDto> leerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(valeService.leerPorId(id));
    }

    @GetMapping("/lista")
    public ResponseEntity<Page<ValeDependeDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(valeService.listar(pageable));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ValeDependeDto> actualizar(
            @PathVariable UUID id, @Valid @RequestBody ValeDto vale) {
        return ResponseEntity.ok(valeService.actualizar(id, vale));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ValeDependeDto> eliminar(@PathVariable UUID id) {
        return ResponseEntity.ok(valeService.eliminar(id));
    }


}
