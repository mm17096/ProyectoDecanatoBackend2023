package com.ues.edu.apidecanatoce.services;

import com.ues.edu.apidecanatoce.entities.Entrada_Salidas;

import java.util.List;

public interface Ientradasalidaservice extends Icrud<Entrada_Salidas> {
    List<Entrada_Salidas> listarPorEstado(int estado);
}
