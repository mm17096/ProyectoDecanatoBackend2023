
package com.ues.edu.apidecanatoce.servicesImpl.documentoVale;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovaleDto;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovalepeticionDto;
import com.ues.edu.apidecanatoce.entities.compras.Compra;
import com.ues.edu.apidecanatoce.entities.documentoVale.Documentovale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.SolicitudValeRepository;
import com.ues.edu.apidecanatoce.repositorys.documentoVale.IDocumentoRepository;
import com.ues.edu.apidecanatoce.services.documentoVale.Idocumentovaleservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class Documentoserviceimpl implements Idocumentovaleservice
{


    private final IDocumentoRepository documentosrepository;
    private  final SolicitudValeRepository solicitudValerepository;

    /*@Override
    public DocumentovaleDto registrar(DocumentovaleDto data) {
        if (documentosrepository.existsByComprobante(data.getComprobante())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El codigo de comprobante ya esta registrado");
        }
            //return documentosrepository.save(data.toEntityComplete()).toDTO();
            return documentosrepository.save(data.toEntityComplete()).toDTO();


    }*/
    @Override
    public DocumentovalepeticionDto registrar(DocumentovaleDto data) {
        System.out.println(data);
        if (documentosrepository.existsByComprobante(data.getComprobante())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El codigo de comprobante ya esta registrado");
        }
        //return documentosrepository.save(data.toEntityComplete()).toDTO();
        return documentosrepository.save(data.toEntityComplete(solicitudValerepository)).toDTO();


    }

    @Override
    public DocumentovaleDto leerPorId(UUID id) {
        return null;
    }

    @Override
    public Page<DocumentovaleDto> listar(Pageable pageable) {
        return null;
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
    public List<DocumentovaleDto> listarSinPagina(UUID id) {
        List<Documentovale> document= this.documentosrepository.findBySolicitudvale_IdSolicitudVale(id);
        return document.stream().map(Documentovale::toDTO2).toList();
    }

    @Override
    public List<DocumentovalepeticionDto> listarSinPaginas() {
        List<Documentovale> documento = this.documentosrepository.findAll();
        return documento.stream().map(Documentovale::toDTO3).toList();
    }

}
