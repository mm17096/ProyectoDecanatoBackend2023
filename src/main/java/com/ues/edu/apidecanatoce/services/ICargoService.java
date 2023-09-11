package com.ues.edu.apidecanatoce.services;

import com.ues.edu.apidecanatoce.dtos.ICargoxEstadoDTO;
import com.ues.edu.apidecanatoce.dtos.cargosDto.CargosDto;
import com.ues.edu.apidecanatoce.dtos.empleados.EmpleadoPeticionDto;
import com.ues.edu.apidecanatoce.entities.cargos.Cargo;


import java.util.List;
import java.util.UUID;


public interface ICargoService extends IcrudTemp<Cargo> {

    List<ICargoxEstadoDTO> findCargoByEstado(Integer estado);

    List<Cargo> findCargoByEstado2(int estado);

    List<Cargo> findAllByNombreCargo(String estado);

    CargosDto leerPorNombre(String nombre);
}
