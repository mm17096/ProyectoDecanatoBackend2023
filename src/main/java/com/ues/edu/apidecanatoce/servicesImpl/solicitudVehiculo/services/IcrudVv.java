package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.services;

import java.util.List;
import java.util.UUID;

public interface IcrudVv <T>{


    List<T> listar();
    T leerPorId(UUID id);

    List<T> listarPorEstado(int estado);
}
