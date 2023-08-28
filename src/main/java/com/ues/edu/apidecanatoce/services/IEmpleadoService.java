package com.ues.edu.apidecanatoce.services;

import com.ues.edu.apidecanatoce.dtos.compras.CompraDto;
import com.ues.edu.apidecanatoce.dtos.compras.CompraPeticionDto;
import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoDto;
import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoPeticionDto;
import com.ues.edu.apidecanatoce.entities.Empleado;

import java.util.List;


public interface IEmpleadoService extends Icrud<Empleado> {
    EmpleadoPeticionDto registrar(EmpleadoDto data);

    List<Empleado> buscarEmpleado(String filtro);

    boolean eliminar(Empleado obj);
}
