package com.ues.edu.apidecanatoce.servicesImpl;

import com.ues.edu.apidecanatoce.entities.Documentos;
import com.ues.edu.apidecanatoce.repositorys.DocumentosESRepo;
import com.ues.edu.apidecanatoce.services.IDocumentoESservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DocumentosESserviceImpl implements IDocumentoESservice {

    private final DocumentosESRepo documentosrepo;
    @Override
    public Documentos registrar(Documentos obj) {
        return this.documentosrepo.save(obj);
    }

    @Override
    public Documentos modificar(Documentos obj) {

        return null;
    }

    @Override
    public List<Documentos> listar() {
        List<Documentos> documentosList= this.documentosrepo.findAll();
        return documentosList;
    }

    @Override
    public Documentos leerPorId(Integer id) {
        return this.documentosrepo.findById(id).get();
    }

    @Override
    public Documentos leerPorDUI(String dui) {
        return null;
    }

    @Override
    public boolean eliminar(Documentos obj) {
        try {
            this.documentosrepo.delete(obj);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Documentos> listarPorEstado(int estado) {
        return null;
    }
}
