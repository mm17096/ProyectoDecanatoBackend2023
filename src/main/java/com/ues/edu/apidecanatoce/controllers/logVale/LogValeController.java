package com.ues.edu.apidecanatoce.controllers.logVale;

import com.ues.edu.apidecanatoce.dtos.asignacionValesDto.vales.LogValeDto;
import com.ues.edu.apidecanatoce.services.logVale.ILogValeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/logVale")
@PreAuthorize("hasAnyRole('ADMIN','ASIS_FINANCIERO','JEFE_FINANACIERO')")
public class LogValeController {

    private final ILogValeService logValeService;

    @GetMapping("/montovalesconsumidosporestado/{estado}")
    public ResponseEntity<List<LogValeDto>> obtenerLogValesPorEstadoYMes(
            @PathVariable int estado) {
        List<LogValeDto> logVales = logValeService.obtenerLogValesPorEstadoYMes(estado);

        return ResponseEntity.ok(logVales);
    }
}
