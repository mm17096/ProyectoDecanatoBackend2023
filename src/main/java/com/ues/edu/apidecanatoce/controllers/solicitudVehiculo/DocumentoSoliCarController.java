package com.ues.edu.apidecanatoce.controllers.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.MensajeRecord;
import com.ues.edu.apidecanatoce.dtos.vehiculo.VehiculoDto;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.DocumentoSoliCar;
import com.ues.edu.apidecanatoce.entities.GenericResponse;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.IDocumentosSoliCarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documentosoli")
@RequiredArgsConstructor
public class DocumentoSoliCarController {
    private final IDocumentosSoliCarService documentosService;

    @GetMapping("/listasinpagina")
    public ResponseEntity<List<DocumentoSoliCar>> listar() {
        List<DocumentoSoliCar> documentList = documentosService.listarSinPagina();
        return ResponseEntity.ok(documentList);
    }

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MensajeRecord> uploadFile(
            @RequestPart(name = "archivo") MultipartFile file,
            @RequestPart(name = "entidad") DocumentoSoliCar soliCar ){
        return ResponseEntity.ok(documentosService.registrar(file, soliCar));
    }

}
