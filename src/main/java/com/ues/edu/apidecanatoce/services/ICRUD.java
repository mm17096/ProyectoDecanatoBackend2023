package com.ues.edu.apidecanatoce.services;

import java.util.List;

public interface ICRUD<T> {
    T registrar(T obj);
    T modificar(T obj);
    List<T> listar();
    T leerPorId(Integer id);
    List<T> listarPorEstado(int estado);
}
