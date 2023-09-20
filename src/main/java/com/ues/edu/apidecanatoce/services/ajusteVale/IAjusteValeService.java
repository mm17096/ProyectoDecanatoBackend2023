package com.ues.edu.apidecanatoce.services.ajusteVale;

import com.ues.edu.apidecanatoce.dtos.ajusteVale.AjusteValeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IAjusteValeService {
    AjusteValeDto registrar(AjusteValeDto data);

    AjusteValeDto leerPorId(UUID id);

    Page<AjusteValeDto> listar(Pageable pageable);

    List<AjusteValeDto> listarSinPagina();

    AjusteValeDto actualizar(UUID id, AjusteValeDto data);

    AjusteValeDto eliminar(UUID id);
}
