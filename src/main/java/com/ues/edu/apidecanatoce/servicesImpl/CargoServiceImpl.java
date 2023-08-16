package com.ues.edu.apidecanatoce.servicesImpl;


import com.ues.edu.apidecanatoce.entities.Cargo;
import com.ues.edu.apidecanatoce.entities.Departamento;
import com.ues.edu.apidecanatoce.repositorys.ICargoRepository;
import com.ues.edu.apidecanatoce.services.ICargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoServiceImpl implements ICargoService {

    private final ICargoRepository cargoService;

    @Autowired
    public CargoServiceImpl(ICargoRepository cargoService){
        this.cargoService = cargoService;
    }

    @Override
    public List<Cargo> listar(){
        List<Cargo> listCargo = this.cargoService.findAll();
        return listCargo;
    }

    @Override
    public Cargo registrar(Cargo obj){return this.cargoService.save(obj);}


    @Override
    public Cargo modificar(Cargo obj){return this.cargoService.save(obj);}

    @Override
    public boolean eliminar(Cargo obj) {
        try{
            this.cargoService.delete(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }



    @Override
    public Cargo leerPorId(Integer id) {
        return this.cargoService.findById(id).orElse(new Cargo());
    }


}
