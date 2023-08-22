package com.ues.edu.apidecanatoce.services;

import com.ues.edu.apidecanatoce.entities.AsignacionVale;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAsignacionValeService extends ICRUD<AsignacionVale>{

    List<AsignacionVale> listarPorEstado(int estado);
}
