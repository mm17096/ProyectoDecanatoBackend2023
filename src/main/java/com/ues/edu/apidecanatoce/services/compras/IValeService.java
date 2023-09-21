package com.ues.edu.apidecanatoce.services.compras;

import com.ues.edu.apidecanatoce.dtos.compras.UsuarioMandarDto;
import com.ues.edu.apidecanatoce.dtos.compras.UsuarioRespuestaDto;
import com.ues.edu.apidecanatoce.dtos.compras.ValeDependeDto;
import com.ues.edu.apidecanatoce.dtos.compras.ValeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IValeService {
    ValeDependeDto registrar(ValeDto data);

    ValeDependeDto leerPorId(UUID id);

    List<ValeDependeDto> devolverValesPorCantidad(int cantidad);

    List<ValeDependeDto> obtenerValesPorMontoTotal(double montoTotal);

    Page<ValeDependeDto> listar(Pageable pageable);

    List<ValeDependeDto> listarSinPagina();

    ValeDependeDto actualizar(UUID id, ValeDto data);

    List<ValeDependeDto> actualizarTodosValesPorCantidad(List<ValeDependeDto> data, String concepto, String idusuariologueado);

    UsuarioRespuestaDto validarUsuario(UsuarioMandarDto data);

    ValeDependeDto eliminar(UUID id);


}
