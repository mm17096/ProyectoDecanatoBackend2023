package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.services.solicitudVehiculo;


import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.DocumentoSoliCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IDocumentosSoliCarService {
    DocumentoSoliCar registrar(MultipartFile data);

    DocumentoSoliCar leerPorId(UUID id);

    Page<DocumentoSoliCar> listar(Pageable pageable);

    List<DocumentoSoliCar> listarSinPagina();

    List<DocumentoSoliCar> listarPorClase();

    DocumentoSoliCar actualizar(DocumentoSoliCar data);

    DocumentoSoliCar eliminar(UUID id);
}
