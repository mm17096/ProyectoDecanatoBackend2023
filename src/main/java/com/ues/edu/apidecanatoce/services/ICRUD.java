package com.ues.edu.apidecanatoce.services;

import java.util.List;

<<<<<<< HEAD
public interface ICRUD<T>{


  T registrar(T obj);
=======
public interface ICRUD<T> {
    T registrar(T obj);
>>>>>>> walterEDR
    T modificar(T obj);
    List<T> listar();
    T leerPorId(Integer id);
    boolean eliminar(T obj);

<<<<<<< HEAD
=======
    List<T> listarPorEstado(int estado);
>>>>>>> walterEDR
}
