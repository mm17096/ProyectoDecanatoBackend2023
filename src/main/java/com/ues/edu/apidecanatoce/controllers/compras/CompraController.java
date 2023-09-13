package com.ues.edu.apidecanatoce.controllers.compras;

import com.ues.edu.apidecanatoce.dtos.compras.CompraInsertarDto;
import com.ues.edu.apidecanatoce.dtos.compras.CompraModificarDto;
import com.ues.edu.apidecanatoce.dtos.compras.CompraPeticionDto;
import com.ues.edu.apidecanatoce.services.compras.ICompraService;
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
@RequestMapping("/api/compra")
public class CompraController {

    private final ICompraService compraService;

    @PostMapping(value = "/insertar")
    public ResponseEntity<CompraPeticionDto> registrar(@Valid @RequestBody CompraInsertarDto compra) {
        return ResponseEntity.ok(compraService.registrar(compra));
    }

    @GetMapping("/listasinpagina")
    public ResponseEntity<List<CompraPeticionDto>> listarSinPagina() {
        List<CompraPeticionDto> compra = compraService.listarSinPagina();
        return ResponseEntity.ok(compra);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<CompraPeticionDto> leerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(compraService.leerPorId(id));
    }

    @GetMapping("/lista")
    public ResponseEntity<Page<CompraPeticionDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(compraService.listar(pageable));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<CompraPeticionDto> actualizar(
            @PathVariable UUID id, @Valid @RequestBody CompraModificarDto compra) {
        return ResponseEntity.ok(compraService.actualizar(id, compra));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<CompraPeticionDto> eliminar(@PathVariable UUID id) {
        return ResponseEntity.ok(compraService.eliminar(id));
    }


}
