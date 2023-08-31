package com.ues.edu.apidecanatoce.servicesImpl.estados;

import com.ues.edu.apidecanatoce.dtos.estados.EstadosDTO;
import com.ues.edu.apidecanatoce.entities.Estados;
import com.ues.edu.apidecanatoce.repositorys.estados.IEstadosRepository;
import com.ues.edu.apidecanatoce.services.estadosService.IEstadosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        }
        return estadosDTO;
    }
}
