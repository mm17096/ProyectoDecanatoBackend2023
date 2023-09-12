package com.ues.edu.apidecanatoce.controllers.documentosVale;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovaleDto;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovalepeticionDto;
import com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.services.Idocumentovaleservice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/document")
@CrossOrigin(origins = "*")
public class Documentosvalecontroller {

    private final Idocumentovaleservice idocumentovaleservice;

    @PostMapping(value = "/insertar")
    public ResponseEntity<DocumentovalepeticionDto> registrar(@Valid @RequestBody DocumentovaleDto documentovale) {
        return ResponseEntity.ok(idocumentovaleservice.registrar(documentovale));
    }

    /*@GetMapping
    public ResponseEntity<Page<DocumentovalepeticionDto>> listar(Pageable pageable) {
        return ResponseEntity.ok(idocumentovaleservice.listar(pageable));
    }*/

    @GetMapping
    public ResponseEntity<List<DocumentovaleDto>> listarVehiculos() {
        List<DocumentovaleDto> document = idocumentovaleservice.listarSinPagina();
        return ResponseEntity.ok(document);
    }





}
