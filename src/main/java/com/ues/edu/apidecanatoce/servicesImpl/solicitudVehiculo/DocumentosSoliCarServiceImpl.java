package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.MensajeRecord;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.DocumentoSoliCar;
import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.IDocumentoSoliCarRepository;
import com.ues.edu.apidecanatoce.services.PathService;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.IDocumentosSoliCarService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentosSoliCarServiceImpl implements IDocumentosSoliCarService{

    @Autowired
    private IDocumentoSoliCarRepository documentosRepository;

    private final PathService pathService;
    private final HttpServletRequest request;

    @Override
    public MensajeRecord registrar(MultipartFile archivo, DocumentoSoliCar soliCar) {
        try {
            // Guardar la imagen en la carpeta del proyecto
            String filename = pathService.generateFileName(archivo);
            pathService.storeFile(archivo, filename);
            Path destinationFile = pathService.generatePath(filename);
            Files.write(destinationFile, archivo.getBytes());

            // Guardar la URL de la imagen en el campo urlfoto y el nombre en nombrefoto del vehículo
            soliCar.setNombreDocumento(filename);
            soliCar.setUrlDocumento(destinationFile.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        documentosRepository.save(soliCar);

        return new MensajeRecord("exito se guardo");
    }

    public String generateUrlImage(String imageName) {
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        return host + "/api/documentosoli/file/" + imageName;
    }

    @Override
    public DocumentoSoliCar leerPorId(UUID id) {
        return null;
    }

    @Override
    public Page<DocumentoSoliCar> listar(Pageable pageable) {
        return null;
    }

    @Override
    public List<DocumentoSoliCar> listarSinPagina() {
        List<DocumentoSoliCar> documentsList= this.documentosRepository.findAll();
        return documentsList;
    }

    @Override
    public DocumentoSoliCar actualizar(DocumentoSoliCar data) {
        return null;
    }

    @Override
    public DocumentoSoliCar eliminar(UUID id) {
        return null;
    }
}
