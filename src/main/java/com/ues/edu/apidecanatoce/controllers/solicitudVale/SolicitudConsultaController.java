package com.ues.edu.apidecanatoce.controllers.solicitudVale;



import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.*;

import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaCompraDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaValeDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaValeGDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudVahiculoConsultaDto;
import com.ues.edu.apidecanatoce.services.solicitudVale.IConsultaValeService;
import com.ues.edu.apidecanatoce.services.solicitudVale.ISolicitudVehiculoConsultaService;
import lombok.RequiredArgsConstructor;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/consulta")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','ASIS_FINANCIERO','JEFE_FINANACIERO')")
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

    @GetMapping("/listarconsultadto")
   // public ResponseEntity<List<ConsultaValeGDto>> listarValesAsignar(@PathVariable UUID id) throws Exception {
    public ResponseEntity<List<ConsultaValeGDto>> listarValesAsignar(@RequestParam("fechaI") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaI,@RequestParam("fechaF") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaF) throws Exception {
        return ResponseEntity.ok(consultaValeService.lisConsultaValeGDto(fechaI, fechaF));
        // return  String.format("parametros: f1 %s, f2 %s",fechaI,fechaF);
    }

    @GetMapping("/listarcompradto")
    public ResponseEntity<List<ConsultaCompraDto>> listarCompraDeVales(@RequestParam("fechaI") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaI, @RequestParam("fechaF") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaF) throws Exception {
        return ResponseEntity.ok(consultaValeService.lisConsultaCompraDto(fechaI, fechaF));
    }

    @GetMapping("/listarvalesdelal/{id}")
    public ResponseEntity<List<ConsultaCantidadValesDelAlDto>> listarValesDelAl(@PathVariable UUID id) throws Exception {
        List<ConsultaCantidadValesDelAlDto> vehiculos = consultaValeService.lisConsultaValesDelAlDto(id);
        return ResponseEntity.ok(vehiculos);

    }
    @GetMapping("/listardocs/{id}")
    public ResponseEntity<List<ConsultaDocumentSoliCarDto>> listarDocumentoSoliCar(@PathVariable UUID id) throws Exception {
        List<ConsultaDocumentSoliCarDto> vehiculos = consultaValeService.lisDocumentSolicar(id);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/listardocv/{id}")
    public ResponseEntity<List<ConsultaSolisValeIdDto>> listarDocumentoVale(@PathVariable UUID id) throws Exception {
        List<ConsultaSolisValeIdDto> vehiculos = consultaValeService.lisDocumentValeid(id);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/listardocvid/{id}")
    public ResponseEntity<List<ConsultaSoliValeIdDto>> listarDocumentoValeid(@PathVariable UUID id) throws Exception {
        List<ConsultaSoliValeIdDto> vehiculos = consultaValeService.lisDocumentVale(id);
        return ResponseEntity.ok(vehiculos);

    }

    @GetMapping("/decano")
    public ResponseEntity<List<ConsultaEmpleadoDto>> ListarDecano() throws Exception {
        List<ConsultaEmpleadoDto> vehiculos = consultaValeService.lisDecano();
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/logsolivhe/{id}")
    public ResponseEntity<List<ConsultaLogSoliVeDto>> listarLogSoliVehi(@PathVariable UUID id) throws Exception {
        List<ConsultaLogSoliVeDto> vehiculos = consultaValeService.lisLogSoliVehi(id);
        return ResponseEntity.ok(vehiculos);

    }

    @GetMapping("/logsolivheid/{id}")
    public ResponseEntity<List<ConsultaLogSoliVeIDDto>> listarLogSoliVehiID(@PathVariable UUID id) throws Exception {
        List<ConsultaLogSoliVeIDDto> vehiculos = consultaValeService.lisLogSoliVehiID(id);
        return ResponseEntity.ok(vehiculos);

    }

    @GetMapping("/logvale/{id}")
    public ResponseEntity<List<ConsultaLogValeDto>> listarLogVale(@PathVariable UUID id) throws Exception {
        List<ConsultaLogValeDto> vehiculos = consultaValeService.lisLogVale(id);
        return ResponseEntity.ok(vehiculos);

    }
    @GetMapping("/idcompra/{id}")
    public ResponseEntity<List<ConsultaIdCompraDto>> listarIdCompra(@PathVariable UUID id) throws Exception {
        List<ConsultaIdCompraDto> vehiculos = consultaValeService.lisIdCompra(id);
        return ResponseEntity.ok(vehiculos);

    }
    @GetMapping("/idvale/{id}")
    public ResponseEntity<List<ConsultaIdValeDto>> listarIdVale(@PathVariable UUID id) throws Exception {
        List<ConsultaIdValeDto> vehiculos = consultaValeService.lisIdVale(id);
        return ResponseEntity.ok(vehiculos);

    }

    @GetMapping("/usuario")
    public ResponseEntity<List<ConsultaUsuarioDto>> listarUsuario(@RequestParam("fechaI") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaI,@RequestParam("fechaF") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaF) throws Exception {
        return ResponseEntity.ok(consultaValeService.lisUsuario(fechaI, fechaF));
    }
}
