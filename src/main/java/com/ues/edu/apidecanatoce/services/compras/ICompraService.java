package com.ues.edu.apidecanatoce.services.compras;


import com.ues.edu.apidecanatoce.dtos.compras.CompraInsertarDto;
import com.ues.edu.apidecanatoce.dtos.compras.CompraModificarDto;
import com.ues.edu.apidecanatoce.dtos.compras.CompraPeticionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ICompraService {
    CompraPeticionDto registrar(CompraInsertarDto data, String idUsuarioLogueado);

    CompraPeticionDto leerPorId(UUID id);

    Page<CompraPeticionDto> listar(Pageable pageable);

    List<CompraPeticionDto> listarSinPagina();

    CompraPeticionDto actualizar(UUID id, CompraModificarDto data);

    CompraPeticionDto eliminar(UUID id);


}
