package com.ues.edu.apidecanatoce.controllers.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.MensajeRecord;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.DocumentoSoliCar;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.IDocumentosSoliCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

    @GetMapping("/document/{nombredocu}")
    public ResponseEntity<byte[]> getImagen(@PathVariable("nombredocu") String filename) {
        byte[] image = new byte[0];
        String path = "./uploads";
        try {
            Path fileName = Paths.get(path, filename);
            image = Files.readAllBytes(fileName);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(image);
    }

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MensajeRecord> uploadFile(
            @RequestPart(name = "archivo") MultipartFile file,
            @RequestPart(name = "entidad") DocumentoSoliCar soliCar ){
        return ResponseEntity.ok(documentosService.registrar(file, soliCar));
    }

}
