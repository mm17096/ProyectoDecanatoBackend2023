package com.ues.edu.apidecanatoce.services;

import com.ues.edu.apidecanatoce.dtos.compras.CompraPeticionDto;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovaleDto;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovalepeticionDto;
import com.ues.edu.apidecanatoce.entities.Documentos;
import com.ues.edu.apidecanatoce.entities.documentoVale.Documentovale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;


public interface Idocumentovaleservice {
    DocumentovalepeticionDto registrar(DocumentovaleDto data);

    DocumentovalepeticionDto leerPorId(UUID id);

    Page<DocumentovalepeticionDto> listar(Pageable pageable);


    DocumentovalepeticionDto actualizar(UUID id, DocumentovaleDto data);

    DocumentovalepeticionDto eliminar(UUID id);

    List<DocumentovaleDto> listarSinPagina();
}
