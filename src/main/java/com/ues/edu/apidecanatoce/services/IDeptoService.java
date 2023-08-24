package com.ues.edu.apidecanatoce.services;

import com.ues.edu.apidecanatoce.entities.Departamento;

import java.util.List;

public interface IDeptoService extends  ICRUD<Departamento> {
    boolean eliminar(Departamento obj);

    List<Departamento> findAllByEstado(int estado);
}
