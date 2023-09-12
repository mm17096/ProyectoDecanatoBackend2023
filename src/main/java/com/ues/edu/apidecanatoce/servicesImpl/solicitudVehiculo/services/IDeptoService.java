package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.services;


import com.ues.edu.apidecanatoce.dtos.departamentoDto.DepartamentoDto;
import com.ues.edu.apidecanatoce.entities.departamentos.Departamento;

import java.util.List;
import java.util.UUID;

public interface IDeptoService extends IcrudTemp<Departamento> {

    boolean eliminar(Departamento obj);

    Departamento leerPorId(UUID id);

    DepartamentoDto leerPorNombre(String nombre);

    List<Departamento> findAllByEstado(int estado);
}
