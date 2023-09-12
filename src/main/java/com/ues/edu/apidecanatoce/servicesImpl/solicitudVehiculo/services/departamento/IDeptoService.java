package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.services.departamento;

import com.ues.edu.apidecanatoce.dtos.departamentoDto.DepartamentoDto;
import com.ues.edu.apidecanatoce.entities.departamentos.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IDeptoService  {


    List<Departamento> findAllByEstado(int estado);

    DepartamentoDto registrar(DepartamentoDto obj);

    DepartamentoDto modificar(UUID id, DepartamentoDto obj);

    DepartamentoDto eliminar(UUID obj);

    DepartamentoDto leerPorId(UUID id);

    DepartamentoDto leerPorNombre(String nombre);

    List<DepartamentoDto> listar();

    Page<DepartamentoDto> listarConPage(Pageable pageable);


}
