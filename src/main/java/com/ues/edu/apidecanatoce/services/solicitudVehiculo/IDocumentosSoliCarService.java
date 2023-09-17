package com.ues.edu.apidecanatoce.services.solicitudVehiculo;


import com.ues.edu.apidecanatoce.dtos.MensajeRecord;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.DocumentoSoliCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IDocumentosSoliCarService {
    MensajeRecord registrar(MultipartFile data, DocumentoSoliCar soliCar);

    DocumentoSoliCar leerPorId(UUID id);

    Page<DocumentoSoliCar> listar(Pageable pageable);

    List<DocumentoSoliCar> listarSinPagina();

    DocumentoSoliCar actualizar(DocumentoSoliCar data);

    DocumentoSoliCar eliminar(UUID id);
}
