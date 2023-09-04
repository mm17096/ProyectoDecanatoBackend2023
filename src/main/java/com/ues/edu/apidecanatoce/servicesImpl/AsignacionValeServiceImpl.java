package com.ues.edu.apidecanatoce.servicesImpl;

import com.ues.edu.apidecanatoce.entities.AsignacionVales.AsignacionVale;
import com.ues.edu.apidecanatoce.repositorys.IAsignacionValeRepository;
import com.ues.edu.apidecanatoce.services.IAsignacionValeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AsignacionValeServiceImpl implements IAsignacionValeService {
    private final IAsignacionValeRepository asignacionValeRepository;
    @Override
    public AsignacionVale registrar(AsignacionVale obj) {
        return asignacionValeRepository.save(obj);
    }

    @Override
    public AsignacionVale modificar(AsignacionVale obj) {
        return asignacionValeRepository.save(obj);
    }

    @Override
    public List<AsignacionVale> listar() {
        return asignacionValeRepository.findAll();
    }

    @Override
    public AsignacionVale leerPorId(UUID id) {
        return asignacionValeRepository.findById(id).get();
    }

    @Override
    public boolean eliminar(AsignacionVale obj) {
       try {
           asignacionValeRepository.delete(obj);
           return true;
       }catch (Exception e){
           return false;
       }
    }

    @Override
    public List<AsignacionVale> listarPorEstado(int estado) {
        return null;
    }
}