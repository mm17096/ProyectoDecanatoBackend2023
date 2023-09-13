package com.ues.edu.apidecanatoce.controllers.compras;

import com.ues.edu.apidecanatoce.dtos.compras.ActualizacionValesRequestDto;
import com.ues.edu.apidecanatoce.dtos.compras.ValeDependeDto;
import com.ues.edu.apidecanatoce.dtos.compras.ValeDto;
import com.ues.edu.apidecanatoce.services.compras.IValeService;
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
@RequestMapping("/api/vale")
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

    @GetMapping("/devolucioncantidad/{cantidad}")
    public ResponseEntity<List<ValeDependeDto>> devolverValesPorCantidad(@PathVariable int cantidad) {
        List<ValeDependeDto> valesDevueltos = valeService.devolverValesPorCantidad(cantidad);
        return ResponseEntity.ok(valesDevueltos);
    }

    @GetMapping("/devolucionmonto/{monto}")
    public ResponseEntity<List<ValeDependeDto>> obtenerValesPorMontoTotal(@PathVariable double monto) {
        List<ValeDependeDto> valesDevueltos = valeService.obtenerValesPorMontoTotal(monto);
        return ResponseEntity.ok(valesDevueltos);
    }

    @GetMapping("/listasinpagina")
    public ResponseEntity<List<ValeDependeDto>> listarSinPagina() {
        List<ValeDependeDto> vale = valeService.listarSinPagina();
        return ResponseEntity.ok(vale);
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

    @PutMapping("/actualizarValesCantidad")
    public ResponseEntity<List<ValeDependeDto>> actualizarVales(
            @RequestBody ActualizacionValesRequestDto request
    ) {
        List<ValeDependeDto> valesDto = request.getVales();
        UUID idProveedor = request.getIdProveedor();
        String concepto = request.getConcepto();
        return ResponseEntity.ok(valeService.actualizarTodosValesPorCantidad(valesDto, idProveedor, concepto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ValeDependeDto> eliminar(@PathVariable UUID id) {
        return ResponseEntity.ok(valeService.eliminar(id));
    }
}
