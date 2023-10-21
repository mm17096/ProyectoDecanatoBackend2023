package com.ues.edu.apidecanatoce.controllers.compras;

import com.ues.edu.apidecanatoce.dtos.compras.CompraInsertarDto;
import com.ues.edu.apidecanatoce.dtos.compras.CompraModificarDto;
import com.ues.edu.apidecanatoce.dtos.compras.CompraPeticionDto;
import com.ues.edu.apidecanatoce.dtos.compras.CompraUsuarioRequestDto;
import com.ues.edu.apidecanatoce.services.compras.ICompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/compra")
@PreAuthorize("hasAnyRole('ADMIN','ASIS_FINANCIERO','JEFE_FINANACIERO')")
public class CompraController {

    private final ICompraService compraService;

    @PostMapping(value = "/insertar")
    public ResponseEntity<CompraPeticionDto> registrar(@Valid @RequestBody CompraUsuarioRequestDto request) {
        CompraInsertarDto compra = request.getCompra();
        String idUsuarioLogueado = request.getIdusuariologueado();
        return ResponseEntity.ok(compraService.registrar(compra, idUsuarioLogueado));
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

    @GetMapping("/listarPorRangoDeFechas")
    public ResponseEntity<List<CompraPeticionDto>> listarPorRangoDeFechas(
            @RequestParam(value = "fechaInicial", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicial,
            @RequestParam(value = "fechaFinal", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFinal) {
        List<CompraPeticionDto> comprasEnRango = compraService.listarComprasPorRangoDeFechas(fechaInicial, fechaFinal);
        return ResponseEntity.ok(comprasEnRango);
    }

}
