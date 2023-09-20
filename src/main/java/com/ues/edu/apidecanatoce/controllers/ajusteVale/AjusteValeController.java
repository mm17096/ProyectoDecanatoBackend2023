package com.ues.edu.apidecanatoce.controllers.ajusteVale;

import com.ues.edu.apidecanatoce.dtos.ajusteVale.AjusteValeDto;
import com.ues.edu.apidecanatoce.services.ajusteVale.IAjusteValeService;
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
@RequestMapping("/api/ajustevale")
public class AjusteValeController {

    private final IAjusteValeService ajusteValeService;

    @PostMapping(value = "/insertar")
    public ResponseEntity<AjusteValeDto> registrar(@Valid @RequestBody AjusteValeDto ajusteVale) {
        return ResponseEntity.ok(ajusteValeService.registrar(ajusteVale));
    }

    @GetMapping("/listasinpagina")
    public ResponseEntity<List<AjusteValeDto>> listarSinPagina() {
        List<AjusteValeDto> ajusteVale = ajusteValeService.listarSinPagina();
        return ResponseEntity.ok(ajusteVale);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<AjusteValeDto> leerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ajusteValeService.leerPorId(id));
    }

    @GetMapping("/lista")
    public ResponseEntity<Page<AjusteValeDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(ajusteValeService.listar(pageable));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<AjusteValeDto> actualizar(
            @PathVariable UUID id, @Valid @RequestBody AjusteValeDto ajusteVale) {
        return ResponseEntity.ok(ajusteValeService.actualizar(id, ajusteVale));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<AjusteValeDto> eliminar(@PathVariable UUID id) {
        return ResponseEntity.ok(ajusteValeService.eliminar(id));
    }


}
