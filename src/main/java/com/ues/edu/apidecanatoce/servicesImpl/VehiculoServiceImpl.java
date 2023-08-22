package com.ues.edu.apidecanatoce.servicesImpl;

import com.ues.edu.apidecanatoce.entities.Vehiculo;
import com.ues.edu.apidecanatoce.repositorys.IVehiculoRepository;
import com.ues.edu.apidecanatoce.services.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

    @Autowired
    private IVehiculoRepository vehiculoRepository;

    public Vehiculo registrar(Vehiculo obj) {
        return this.vehiculoRepository.save(obj);
    }

    @Override
    public Vehiculo modificar(Vehiculo obj) {
        return this.vehiculoRepository.save(obj);
    }

    @Override
    public List<Vehiculo> listar() {
        return this.vehiculoRepository.findAll();
    }

    @Override
    public Vehiculo leerPorId(Integer id) {
        return this.vehiculoRepository.findById(id).orElse(null);
    }

    @Override
    public Vehiculo leerPorDUI(String dui) {
        return null;
    }

    @Override
    public boolean eliminar(Vehiculo obj) {
        // TODO Auto-generated method stub
        try {
            this.vehiculoRepository.delete(obj);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

}
