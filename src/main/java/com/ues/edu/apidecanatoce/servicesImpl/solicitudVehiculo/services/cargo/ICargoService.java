package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.services.cargo;

import com.ues.edu.apidecanatoce.dtos.cargosDto.CargosDto;
import com.ues.edu.apidecanatoce.entities.cargos.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface ICargoService  {

//List<ICargoxEstadoDTO> findCargoByEstado(Integer estado);

    List<Cargo> findCargoByEstado2(int estado);

    List<Cargo> findAllByNombreCargo(String estado);

    CargosDto registrar(CargosDto obj);

    CargosDto modificar(UUID id, CargosDto obj);

    CargosDto eliminar(UUID obj);

    CargosDto leerPorId(UUID id);

    CargosDto leerPorNombre(String nombre);

    List<CargosDto> listar();

    Page<CargosDto> listarConPage(Pageable pageable);




}
