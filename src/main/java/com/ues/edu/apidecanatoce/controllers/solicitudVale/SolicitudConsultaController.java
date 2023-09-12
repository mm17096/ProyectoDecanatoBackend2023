package com.ues.edu.apidecanatoce.controllers.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudVahiculoConsultaDto;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoPeticionDtO;
import com.ues.edu.apidecanatoce.services.estados.IEstadosService;
import com.ues.edu.apidecanatoce.services.solicitudVale.ISolicitudVehiculoConsultaService;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.ISolicitudVehiculoServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/consulta")
@RequiredArgsConstructor
public class SolicitudConsultaController {
    private final ISolicitudVehiculoConsultaService servicioSolicitudVehiculo;
    private final IEstadosService estadosService;
    @GetMapping("/listapage")
    public ResponseEntity<Page<SolicitudVahiculoConsultaDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(servicioSolicitudVehiculo.listar(pageable));
    }
    //este es un comendario este es un comentario
}
