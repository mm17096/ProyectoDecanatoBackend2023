package com.ues.edu.apidecanatoce.services;

import java.util.List;

public interface Icrud<T> {
    T registrar(T obj);
    T modificar(T obj);
    List<T> listar();
    T leerPorId(Integer id);
    boolean eliminar(T obj);
}

