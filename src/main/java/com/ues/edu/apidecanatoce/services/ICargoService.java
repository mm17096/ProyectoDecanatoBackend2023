package com.ues.edu.apidecanatoce.services;

import com.ues.edu.apidecanatoce.dtos.CargosDto.CargosDto;

import com.ues.edu.apidecanatoce.entities.Cargos.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface ICargoService  {

//List<ICargoxEstadoDTO> findCargoByEstado(Integer estado);

    List<Cargo> findCargoByEstado2(int estado);

    List<Cargo> findAllByNombreCargo(String estado);

    CargosDto registrar(CargosDto obj);

    CargosDto modificar(CargosDto obj);

    CargosDto eliminar(UUID obj);

    CargosDto leerPorId(UUID id);

    List<CargosDto> listar();

    Page<CargosDto> listarConPage(Pageable pageable);




}
