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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
     //   public ResponseEntity<List<ConsultaCantidadValesDelAlDto>> listarValesDelAl(@RequestParam("id") UUID id) throws Exception {
        System.out.println("id: "+ id);
        return ResponseEntity.ok(consultaValeService.lisConsultaValesDelAlDto(id));
    }
   /* @GetMapping("/api")
    public String obtenerDatos(@RequestParam("fecha") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fecha) {
        // Aquí puedes usar la fecha (convertida en LocalDate) en tu lógica de negocio
        return "Fecha recibida: " + fecha;
    }*/
}
