package com.ues.edu.apidecanatoce.controllers.documentosVale;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ues.edu.apidecanatoce.dtos.compras.CompraPeticionDto;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovaleDto;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovalepeticionDto;
import com.ues.edu.apidecanatoce.services.PathService;
import com.ues.edu.apidecanatoce.services.documentoVale.ArchivoService;
import com.ues.edu.apidecanatoce.services.documentoVale.Idocumentovaleservice;
import com.ues.edu.apidecanatoce.servicesImpl.documentoVale.Documentoserviceimpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/document")
@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN','VIGILANTE','ASIS_FINANCIERO','JEFE_FINANACIERO')")
public class Documentosvalecontroller {


    private final Idocumentovaleservice idocumentovaleservice;
    private final Documentoserviceimpl idocumentovaleserviceimpl;
    private final PathService pathService;
    private final HttpServletRequest request;
    private final ArchivoService archivoService;

    public String generateUrlImage(String imageName) {
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        //return host + "/document/imagen/" + imageName;
        return imageName;
    }

    /*@PostMapping("/insertar")
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
    }*/
    @PostMapping("/insertar")
    public DocumentovalepeticionDto InsertarDocumento(@PathVariable MultipartFile imagen, @Valid @RequestParam("document") String documentoJson) {
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
            return this.idocumentovaleserviceimpl.registrar(docume);
        } catch (IOException e) {
            // Manejar la excepción en caso de que ocurra un error al procesar la imagen
            e.printStackTrace();
            String mensajeError = "Ocurrió un error al procesar la imagen.";
            return null;// Devuelve una respuesta de error
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DocumentovaleDto>> listarVehiculos(@PathVariable UUID id) {

        return ResponseEntity.ok(idocumentovaleservice.listarSinPagina(id));
    }

    @GetMapping(value = "/descarga/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException{
        Resource file = archivoService.downloadFile(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header("Content-Disposition", "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);

    }

    @GetMapping("/listasinpagina")
    public ResponseEntity<List<DocumentovalepeticionDto>> listarSinPagina() {
        List<DocumentovalepeticionDto> ducumento = idocumentovaleservice.listarSinPaginas();
        return ResponseEntity.ok(ducumento);
    }

}
