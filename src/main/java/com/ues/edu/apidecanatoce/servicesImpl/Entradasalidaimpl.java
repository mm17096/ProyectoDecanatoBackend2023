package com.ues.edu.apidecanatoce.servicesImpl;

import com.ues.edu.apidecanatoce.entities.Entrada_Salidas;
import com.ues.edu.apidecanatoce.repositorys.Entradasalidarepo;
import com.ues.edu.apidecanatoce.services.Ientradasalidaservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class Entradasalidaimpl implements Ientradasalidaservice {

    private  final Entradasalidarepo entradaSalidaRepo;
    @Override
    public Entrada_Salidas registrar(Entrada_Salidas obj) {
        return this.entradaSalidaRepo.save(obj);
    }

    @Override
    public Entrada_Salidas modificar(Entrada_Salidas obj) {
        return null;
    }

    @Override
    public List<Entrada_Salidas> listar() {
        List<Entrada_Salidas> entradaSalidasList = this.entradaSalidaRepo.findAll();
        return entradaSalidasList;
    }

    @Override
    public Entrada_Salidas leerPorId(UUID id) {
        return this.entradaSalidaRepo.findById(id).get();
    }

    @Override
    public boolean eliminar(Entrada_Salidas obj) {
        try {
            this.entradaSalidaRepo.delete(obj);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public List<Entrada_Salidas> listarPorEstado(int estado) {
        return null;
    }
}
