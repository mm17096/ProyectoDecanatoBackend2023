package com.ues.edu.apidecanatoce.services;

import com.ues.edu.apidecanatoce.dtos.ICargoxEstadoDTO;
import com.ues.edu.apidecanatoce.entities.Cargo;

import java.util.List;


public interface ICargoService extends ICrud<Cargo> {

List<ICargoxEstadoDTO> findCargoByEstado(Integer estado);

    List<Cargo> findCargoByEstado2(int estado);

    List<Cargo> findAllByNombreCargo(String estado);


}
