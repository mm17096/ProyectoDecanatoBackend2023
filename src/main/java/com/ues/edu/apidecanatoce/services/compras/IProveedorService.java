package com.ues.edu.apidecanatoce.services.compras;
import com.ues.edu.apidecanatoce.dtos.compras.ProveedorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface IProveedorService {
    ProveedorDto registrar(ProveedorDto data);

    ProveedorDto leerPorId(UUID id);

    Page<ProveedorDto> listar(Pageable pageable);

    List<ProveedorDto> listarSinPagina();

    ProveedorDto actualizar(UUID id, ProveedorDto data);

    ProveedorDto eliminar(UUID id);


}
