package com.ues.edu.apidecanatoce.controllers.solicitudVehiculo;

import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.DocumentoSoliCar;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.IDocumentosSoliCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/documentosoli")
@RequiredArgsConstructor
public class DocumentoSoliCarController {
    private final IDocumentosSoliCarService documentosService;

    @PostMapping("/upload")
    public ResponseEntity<DocumentoSoliCar> uploadFile(@RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(documentosService.registrar(file));
    }

}
