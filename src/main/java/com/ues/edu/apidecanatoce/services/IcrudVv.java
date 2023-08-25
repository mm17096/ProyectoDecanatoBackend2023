package com.ues.edu.apidecanatoce.services;

import java.util.List;

public interface IcrudVv <T>{


    List<T> listar();
    T leerPorId(Integer id);

    List<T> listarPorEstado(int estado);
}
