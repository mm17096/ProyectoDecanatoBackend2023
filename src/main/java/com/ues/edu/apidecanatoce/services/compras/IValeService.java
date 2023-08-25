package com.ues.edu.apidecanatoce.services.compras;


import com.ues.edu.apidecanatoce.dtos.compras.ValeDependeDto;
import com.ues.edu.apidecanatoce.dtos.compras.ValeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IValeService {
    ValeDependeDto registrar(ValeDto data);

    ValeDependeDto leerPorId(UUID id);

    Page<ValeDependeDto> listar(Pageable pageable);

    ValeDependeDto actualizar(UUID id, ValeDto data);

    ValeDependeDto eliminar(UUID id);


}
