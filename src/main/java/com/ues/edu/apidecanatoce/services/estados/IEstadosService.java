package com.ues.edu.apidecanatoce.services.estados;

import com.ues.edu.apidecanatoce.dtos.estados.EstadosDTO;

import java.util.List;

public interface IEstadosService {
    List<EstadosDTO> estadosSoliVe();

    List<EstadosDTO> estados();

    EstadosDTO leerPorId(Integer id);

    EstadosDTO leerPorNombre(String nombre);
}
