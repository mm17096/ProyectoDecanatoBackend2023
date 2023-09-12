package com.ues.edu.apidecanatoce.servicesImpl.estados;

import com.ues.edu.apidecanatoce.dtos.estados.EstadosDTO;
import com.ues.edu.apidecanatoce.entities.estados.Estados;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.estados.IEstadosRepository;
import com.ues.edu.apidecanatoce.servicesImpl.solicitudVehiculo.services.estados.IEstadosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EstadosServiceImpl implements IEstadosService {
    @Autowired
    private IEstadosRepository estadosRepository;

    @Override
    public List<EstadosDTO> estadosSoliVe(){
        List<Estados> estados = this.estadosRepository.findAll();
        Estados est = new Estados();
        est.setCodigoEstado(0);
        est.setNombreEstado("TODAS");
        estados.add(0, est);
        List<EstadosDTO> estadosDTO = new ArrayList<>();
        for (Estados estado : estados) {
            if (estado.getCodigoEstado() >= 0 && estado.getCodigoEstado() <= 7) {
                estadosDTO.add(estado.toDTO());
            }
            if (estado.getCodigoEstado() == 15){
                estadosDTO.add(estado.toDTO());
            }
        }
        return estadosDTO;
    }

    @Override
    public List<EstadosDTO> estados(){
        List<Estados> estados = this.estadosRepository.findAll();
        Estados est = new Estados();
        est.setCodigoEstado(0);
        est.setNombreEstado("TODAS");
        estados.add(0, est);
        List<EstadosDTO> estadosDTO = new ArrayList<>();
        for (Estados estado : estados) {
                estadosDTO.add(estado.toDTO());
        }
        return estadosDTO;
    }

    @Override
    public EstadosDTO leerPorId(Integer id) {
        Estados estado = estadosRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro empleado"));
        return estado.toDTO();
    }

    @Override
    public EstadosDTO leerPorNombre(String nombre) {
        List<Estados> estados = estadosRepository.findAll();
        Estados estadOb = new Estados();
        for (Estados estado: estados) {
            if(estado.getNombreEstado().equals(nombre)){
                estadOb = estado;
            }
        }
        return estadOb.toDTO();
    }
}
