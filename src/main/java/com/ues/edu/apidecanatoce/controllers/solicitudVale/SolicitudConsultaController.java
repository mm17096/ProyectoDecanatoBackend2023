package com.ues.edu.apidecanatoce.controllers.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaValeDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudVahiculoConsultaDto;
import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.SolicitudVehiculoPeticionDtO;
import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import com.ues.edu.apidecanatoce.services.estados.IEstadosService;
import com.ues.edu.apidecanatoce.services.solicitudVale.IConsultaValeService;
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

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/consulta")
@RequiredArgsConstructor
public class SolicitudConsultaController {
    private final ISolicitudVehiculoConsultaService servicioSolicitudVehiculo;
    private final IConsultaValeService consultaValeService;
    @GetMapping("/listapage")
    public ResponseEntity<Page<SolicitudVahiculoConsultaDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(servicioSolicitudVehiculo.listar(pageable));
    }

    @GetMapping("/listaconsulta")
    public ResponseEntity<List<ConsultaValeDto>> listarVehiculos() {
        List<ConsultaValeDto> consulta = consultaValeService.listarSinPagina();
        return ResponseEntity.ok(consulta);
    }
}