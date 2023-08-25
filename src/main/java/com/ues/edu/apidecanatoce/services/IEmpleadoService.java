package com.ues.edu.apidecanatoce.services;

import com.ues.edu.apidecanatoce.entities.Empleado;

import java.util.List;

public interface IEmpleadoService extends Icrud<Empleado> {

    List<Empleado> buscarEmpleado(String filtro);

    boolean eliminar(Empleado obj);
}
