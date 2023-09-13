package com.ues.edu.apidecanatoce.servicesImpl;

import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovaleDto;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovalepeticionDto;
import com.ues.edu.apidecanatoce.entities.documentoVale.Documentovale;
import com.ues.edu.apidecanatoce.repositorys.SolicitudValeRepository;
import com.ues.edu.apidecanatoce.repositorys.documentoVale.Documentosrepository;
import com.ues.edu.apidecanatoce.services.Idocumentovaleservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class Documentoserviceimpl implements Idocumentovaleservice {

    private final Documentosrepository documentosrepository;
    private final SolicitudValeRepository solicitudvalerepository;

    @Override
    public DocumentovalepeticionDto registrar(DocumentovaleDto data) {

        return documentosrepository.save(data.toEntityComplete(solicitudvalerepository)).toDTO();
    }

    @Override
    public DocumentovalepeticionDto leerPorId(UUID id) {
        return null;
    }


    @Override
    public Page<DocumentovalepeticionDto> listar(Pageable pageable) {
        Page<Documentovale> documento = documentosrepository.findAll(pageable);
        return documento.map(Documentovale::toDTO);
    }

    @Override
    public DocumentovalepeticionDto actualizar(UUID id, DocumentovaleDto data) {
        return null;
    }

    @Override
    public DocumentovalepeticionDto eliminar(UUID id) {
        return null;
    }


    @Override
    public List<DocumentovaleDto> listarSinPagina() {
        List<Documentovale> document= this.documentosrepository.findAll();
        return document.stream().map(Documentovale::toDTO2).toList();
    }

}
