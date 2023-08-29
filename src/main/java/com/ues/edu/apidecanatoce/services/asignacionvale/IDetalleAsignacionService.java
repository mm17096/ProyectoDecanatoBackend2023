package com.ues.edu.apidecanatoce.services.asignacionvale;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.AsignacionValeDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.AsignacionValeOutDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.DetalleAsignacionDto;
import com.ues.edu.apidecanatoce.services.Icrud;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

@Service
public interface IDetalleAsignacionService {
     DetalleAsignacionDto registrar(DetalleAsignacionDto data);
     DetalleAsignacionDto leerPorId(UUID id);
     Page<DetalleAsignacionDto> listar(Pageable pageable);
     DetalleAsignacionDto actualizar(String id, DetalleAsignacionDto data);
     DetalleAsignacionDto eliminar(String id);
}
