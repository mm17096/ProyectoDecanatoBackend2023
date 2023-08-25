package com.ues.edu.apidecanatoce.services;

import java.util.List;

public interface ICrud<T> {
    T registrar(T obj);
    T modificar(T obj);
    List<T> listar();
    T leerPorId(String id);
    boolean eliminar(T obj);
}

