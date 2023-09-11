package com.ues.edu.apidecanatoce.services;

import com.ues.edu.apidecanatoce.dtos.DepartamentoDto.DepartamentoDto;
import com.ues.edu.apidecanatoce.entities.Departamentos.Departamento;

import java.util.List;
import java.util.UUID;

public interface IDeptoService extends IcrudTemp<Departamento> {

    boolean eliminar(Departamento obj);

    Departamento leerPorId(UUID id);

    DepartamentoDto leerPorNombre(String nombre);

    List<Departamento> findAllByEstado(int estado);
}
