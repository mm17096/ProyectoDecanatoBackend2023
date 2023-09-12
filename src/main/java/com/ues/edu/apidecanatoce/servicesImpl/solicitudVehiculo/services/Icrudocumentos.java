package com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.services;

import java.util.List;

public interface Icrudocumentos <T>{
    T registrar(T obj);
    T modificar(T obj);
    List<T> listar();
    T leerPorId(Integer id);
    boolean eliminar(T obj);
}
