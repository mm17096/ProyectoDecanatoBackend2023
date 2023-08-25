package com.ues.edu.apidecanatoce.services;

import java.util.List;
import java.util.UUID;

public interface ICrudSolVe<T> {
    T registrar(T obj);
    T modificar(T obj);
    List<T> listar();
    T leerPorId(UUID id);
    boolean eliminar(T obj);

    List<T> listarPorEstado(int estado);
}
