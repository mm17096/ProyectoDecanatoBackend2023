package com.ues.edu.apidecanatoce.services.compras;


import com.ues.edu.apidecanatoce.dtos.compras.CompraDto;
import com.ues.edu.apidecanatoce.dtos.compras.CompraPeticionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ICompraService {
    CompraPeticionDto registrar(CompraDto data);

    CompraPeticionDto leerPorId(UUID id);

    Page<CompraPeticionDto> listar(Pageable pageable);

    CompraPeticionDto actualizar(UUID id, CompraDto data);

    CompraPeticionDto eliminar(UUID id);


}
