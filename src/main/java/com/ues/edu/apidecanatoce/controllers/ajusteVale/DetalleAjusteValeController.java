package com.ues.edu.apidecanatoce.controllers.ajusteVale;

import com.ues.edu.apidecanatoce.dtos.ajusteVale.DetalleAjusteValeDto;
import com.ues.edu.apidecanatoce.services.ajusteVale.IDetalleAjusteValeService;
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
@RequestMapping("/api/detalleajustevale")
public class DetalleAjusteValeController {

    private final IDetalleAjusteValeService detalleAjusteValeService;

    @PostMapping(value = "/insertar")
    public ResponseEntity<DetalleAjusteValeDto> registrar(@Valid @RequestBody DetalleAjusteValeDto detalleAjusteVale) {
        return ResponseEntity.ok(detalleAjusteValeService.registrar(detalleAjusteVale));
    }

    @GetMapping("/listasinpagina")
    public ResponseEntity<List<DetalleAjusteValeDto>> listarSinPagina() {
        List<DetalleAjusteValeDto> detalleAjusteVale = detalleAjusteValeService.listarSinPagina();
        return ResponseEntity.ok(detalleAjusteVale);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<DetalleAjusteValeDto> leerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(detalleAjusteValeService.leerPorId(id));
    }

    @GetMapping("/lista")
    public ResponseEntity<Page<DetalleAjusteValeDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(detalleAjusteValeService.listar(pageable));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<DetalleAjusteValeDto> actualizar(
            @PathVariable UUID id, @Valid @RequestBody DetalleAjusteValeDto detalleAjusteVale) {
        return ResponseEntity.ok(detalleAjusteValeService.actualizar(id, detalleAjusteVale));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<DetalleAjusteValeDto> eliminar(@PathVariable UUID id) {
        return ResponseEntity.ok(detalleAjusteValeService.eliminar(id));
    }


}
