package com.ues.edu.apidecanatoce.services;


import com.ues.edu.apidecanatoce.entities.AsignacionVales.AsignacionVale;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAsignacionValeService extends Icrud<AsignacionVale> {

    List<AsignacionVale> listarPorEstado(int estado);
}
