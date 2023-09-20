package com.ues.edu.apidecanatoce.services.ajusteVale;

import com.ues.edu.apidecanatoce.dtos.ajusteVale.DetalleAjusteValeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IDetalleAjusteValeService {
    DetalleAjusteValeDto registrar(DetalleAjusteValeDto data);

    DetalleAjusteValeDto leerPorId(UUID id);

    Page<DetalleAjusteValeDto> listar(Pageable pageable);

    List<DetalleAjusteValeDto> listarSinPagina();

    DetalleAjusteValeDto actualizar(UUID id, DetalleAjusteValeDto data);

    DetalleAjusteValeDto eliminar(UUID id);
}
