package com.ues.edu.apidecanatoce.services;

import com.ues.edu.apidecanatoce.entities.Entrada_Salidas;

import java.util.List;

public interface IEntradaSalidaService extends ICRUD<Entrada_Salidas>{
    List<Entrada_Salidas> listarPorEstado(int estado);
}
