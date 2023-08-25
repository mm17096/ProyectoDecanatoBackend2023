package com.ues.edu.apidecanatoce.servicesImpl;

import com.ues.edu.apidecanatoce.entities.DocumentoSoliCar;
import com.ues.edu.apidecanatoce.entities.Documentos;
import com.ues.edu.apidecanatoce.repositorys.IDocumentoSoliCarRepository;
import com.ues.edu.apidecanatoce.services.IDocumentosSoliCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentosSoliCarServiceImpl implements IDocumentosSoliCarService {

    @Autowired
    private IDocumentoSoliCarRepository documentosRepository;

    @Override
    public DocumentoSoliCar registrar(DocumentoSoliCar obj) {
        return this.documentosRepository.save(obj);
    }

    @Override
    public DocumentoSoliCar modificar(DocumentoSoliCar obj) {
        return this.documentosRepository.save(obj);
    }

    @Override
    public List<DocumentoSoliCar> listar() {
        return this.documentosRepository.findAll();
    }

    @Override
    public DocumentoSoliCar leerPorId(Integer id) {
        return this.documentosRepository.findById(id).orElse(null);
    }

    @Override
    public boolean eliminar(DocumentoSoliCar obj) {
        // TODO Auto-generated method stub
        try {
            this.documentosRepository.delete(obj);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
}
