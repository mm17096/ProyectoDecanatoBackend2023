package com.ues.edu.apidecanatoce.controllers.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.compras.ValeDependeDto;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovalepeticionDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudValeADto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudValeDependeDto;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoDto;
import com.ues.edu.apidecanatoce.services.solicitudVale.ISolicitudValeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/solicitudvale")
@RequiredArgsConstructor
public class SolicitudValeController {
    private final ISolicitudValeService solicitudValeService;
     @PostMapping(value = "/insertar")
      public ResponseEntity<SolicitudValeDependeDto> registrar(@RequestBody SolicitudValeADto vale) {
          return ResponseEntity.ok(solicitudValeService.registrar(vale));
      }
    @GetMapping("/lista/{id}")
    public ResponseEntity<SolicitudValeDependeDto> leerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(solicitudValeService.leerPorId(id));
    }
    @GetMapping("/lista")
    public ResponseEntity<Page<SolicitudValeDependeDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(solicitudValeService.listar(pageable));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<SolicitudValeDependeDto> actualizar(
            @PathVariable UUID id, @RequestBody SolicitudValeADto vale) {
        return ResponseEntity.ok(solicitudValeService.actualizar(id, vale));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<SolicitudValeDependeDto> eliminar(@PathVariable UUID id) {
        return ResponseEntity.ok(solicitudValeService.eliminar(id));
    }

    @GetMapping("/listasinpagina")
    public ResponseEntity<List<SolicitudValeDependeDto>> listarSinPagina() {
        List<SolicitudValeDependeDto> solicitud = solicitudValeService.listarSinPaginas();
        return ResponseEntity.ok(solicitud);
    }
}
