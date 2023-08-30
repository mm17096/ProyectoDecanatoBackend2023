package com.ues.edu.apidecanatoce.services;

import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoDto;
import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoPeticionDto;
import com.ues.edu.apidecanatoce.entities.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface IEmpleadoService {
    EmpleadoPeticionDto registrar(EmpleadoDto data);

    Page<EmpleadoPeticionDto> listar(Pageable pageable);

    EmpleadoPeticionDto leerPorId(UUID id);

    EmpleadoPeticionDto actualizar(UUID id, EmpleadoDto data);

}
