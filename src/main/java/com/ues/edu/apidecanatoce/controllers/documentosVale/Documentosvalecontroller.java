package com.ues.edu.apidecanatoce.controllers.documentosVale;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovaleDto;
import com.ues.edu.apidecanatoce.services.PathService;
import com.ues.edu.apidecanatoce.services.documentoVale.Idocumentovaleservice;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/document")
@CrossOrigin(origins = "*")
public class Documentosvalecontroller {


    private final Idocumentovaleservice idocumentovaleservice;
    private final PathService pathService;
    private final HttpServletRequest request;

    public String generateUrlImage(String imageName) {
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        //return host + "/document/imagen/" + imageName;
        return imageName;
    }

    @PostMapping("/insertar")
    public DocumentovaleDto InsertarDocumento(@PathVariable MultipartFile imagen, @Valid @RequestParam("document") String documentoJson) {
        try {
            // Crear el objetmapper y agregar el mapeo de fechas
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            DocumentovaleDto docume = objectMapper.readValue(documentoJson, DocumentovaleDto.class);

            // Guardar la nueva imagen en la carpeta del proyecto
            String filename = pathService.generateFileName(imagen);
            pathService.storeFile(imagen, filename);
            Path destinationFile = pathService.generatePath(filename);
            Files.write(destinationFile, imagen.getBytes());

            // Guardar la URL de la imagen en el campo urlfoto y el nombre en nombrefoto del vehículo
            docume.setFoto(generateUrlImage(filename));
            docume.setUrl(destinationFile.toString());

            // Guardar los cambios del vehículo en la base de datos
            return this.idocumentovaleservice.registrar(docume);
        } catch (IOException e) {
            // Manejar la excepción en caso de que ocurra un error al procesar la imagen
            e.printStackTrace();
            String mensajeError = "Ocurrió un error al procesar la imagen.";
            return null;// Devuelve una respuesta de error
        }
    }

    @GetMapping
    public ResponseEntity<List<DocumentovaleDto>> listarVehiculos() {
        List<DocumentovaleDto> document = idocumentovaleservice.listarSinPagina();
        return ResponseEntity.ok(document);
    }





}
