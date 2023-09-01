package com.ues.edu.apidecanatoce.servicesImpl;


import com.ues.edu.apidecanatoce.dtos.ICargoxEstadoDTO;
import com.ues.edu.apidecanatoce.entities.Cargos.Cargo;
import com.ues.edu.apidecanatoce.repositorys.ICargoRepository;
import com.ues.edu.apidecanatoce.services.ICargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CargoServiceImpl implements ICargoService {

    private final ICargoRepository cargoService;



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
    public List<Cargo> listarPorEstado(int estado) {
        return null;
    }


    @Override
    public Cargo leerPorId(UUID id) {
        return this.cargoService.findById(id).orElse(new Cargo());
    }

    @Override

    public List<ICargoxEstadoDTO> findCargoByEstado( Integer estado)  {
        List<ICargoxEstadoDTO> listCargo = this.cargoService.findCargoByEstado(estado);
        return listCargo;
    }

    @Override
    public List<Cargo> findCargoByEstado2(int estado) {
        return this.cargoService.findAllByEstado(estado);
    }

    @Override
    public List<Cargo> findAllByNombreCargo(String nombre) {
        return this.cargoService.findAllByNombreCargo(nombre);
    }


}
