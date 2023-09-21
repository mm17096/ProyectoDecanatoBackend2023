package com.ues.edu.apidecanatoce.services.documentoVale;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovaleDto;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.DocumentovalepeticionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface Idocumentovaleservice {

    //DocumentovaleDto registrar(DocumentovaleDto data);
    DocumentovalepeticionDto registrar(DocumentovaleDto data);

    DocumentovaleDto leerPorId(UUID id);

    Page<DocumentovaleDto> listar(Pageable pageable);


    DocumentovalepeticionDto actualizar(UUID id, DocumentovaleDto data);

    DocumentovalepeticionDto eliminar(UUID id);

    List<DocumentovaleDto> listarSinPagina(UUID id);
    List<DocumentovalepeticionDto> listarSinPaginas();
}
