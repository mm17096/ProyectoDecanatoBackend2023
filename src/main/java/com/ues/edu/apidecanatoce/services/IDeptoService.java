package com.ues.edu.apidecanatoce.services;

import com.ues.edu.apidecanatoce.entities.Departamento;

public interface IDeptoService extends Icrud<Departamento> {
    boolean eliminar(Departamento obj);
}
