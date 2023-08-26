package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo;

import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.DocumentoSoliCar;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.IDocumentoSoliCarRepository;
import com.ues.edu.apidecanatoce.services.solicitudVehiculo.IDocumentosSoliCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentosSoliCarServiceImpl implements IDocumentosSoliCarService{

    @Autowired
    private IDocumentoSoliCarRepository documentosRepository;


    @Override
    public DocumentoSoliCar registrar(MultipartFile data) {
        return null;
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
        return null;
    }

    @Override
    public List<DocumentoSoliCar> listarPorClase() {
        return null;
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
