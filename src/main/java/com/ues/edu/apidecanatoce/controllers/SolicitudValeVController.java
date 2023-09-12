package com.ues.edu.apidecanatoce.controllers;


import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ISolicitudVehiculoRepository;
import com.ues.edu.apidecanatoce.repositorys.SolicitudValeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/solicitudvale")
@CrossOrigin("*")
public class SolicitudValeVController {
    @Autowired
    private ISolicitudVehiculoRepository solicitudVehiculoRepository;
    @Autowired
    private SolicitudValeRepository solicitudValeRepository;

    @GetMapping
    public ResponseEntity<Page<SolicitudVale>> lisraSolicitudVale(Pageable pageable){
        return ResponseEntity.ok(solicitudValeRepository.findAll(pageable));
    }
    @PostMapping
    public ResponseEntity<SolicitudVale> guardarSolicitudvale(@RequestBody SolicitudVale solicitudVale){
        Optional<SolicitudVehiculo> solicitudVehiculoOptional = solicitudVehiculoRepository.findById(solicitudVale.getSolicitudVehiculo().getCodigoSolicitudVehiculo());
        if (!solicitudVehiculoOptional.isPresent()) {
            return  ResponseEntity.unprocessableEntity().build();
        }
        solicitudVale.setSolicitudVehiculo(solicitudVehiculoOptional.get());
        SolicitudVale solicitudVale1Guardado = solicitudValeRepository.save(solicitudVale);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(solicitudVale1Guardado.getIdSolicitudVale()).toUri();
        return ResponseEntity.created(ubicacion).body(solicitudVale1Guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudVale> actulizarSolicitudvale(@PathVariable UUID id, @RequestBody SolicitudVale solicitudVale){
        Optional<SolicitudVehiculo> solicitudVehiculoOptional = solicitudVehiculoRepository.findById(solicitudVale.getSolicitudVehiculo().getCodigoSolicitudVehiculo());
        if (!solicitudVehiculoOptional.isPresent()) {
            return  ResponseEntity.unprocessableEntity().build();
        }

        Optional<SolicitudVale> solicitudVale1Opcional = solicitudValeRepository.findById(id);
        if (!solicitudVale1Opcional.isPresent()) {
            return  ResponseEntity.unprocessableEntity().build();
        }

        solicitudVale.setSolicitudVehiculo(solicitudVehiculoOptional.get());
        solicitudVale.setIdSolicitudVale(solicitudVale1Opcional.get().getIdSolicitudVale());
        solicitudValeRepository.save(solicitudVale);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SolicitudVale> eliminarSalicitudVale(@PathVariable UUID id){
        Optional<SolicitudVale> solicitudVale1Opcional = solicitudValeRepository.findById(id);
        if (!solicitudVale1Opcional.isPresent()) {
            return  ResponseEntity.unprocessableEntity().build();
        }
        solicitudValeRepository.delete(solicitudVale1Opcional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudVale> obtenerlibroPorID(@PathVariable UUID id){
        Optional<SolicitudVale> solicitudVale1Opcional = solicitudValeRepository.findById(id);
        if (!solicitudVale1Opcional.isPresent()) {
            return  ResponseEntity.unprocessableEntity().build();
        }
        return  ResponseEntity.ok(solicitudVale1Opcional.get());
    }

}
