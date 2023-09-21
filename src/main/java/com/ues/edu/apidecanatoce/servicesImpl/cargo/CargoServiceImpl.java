package com.ues.edu.apidecanatoce.servicesImpl.cargo;


import com.ues.edu.apidecanatoce.dtos.cargosDto.CargosDto;
import com.ues.edu.apidecanatoce.entities.cargos.Cargo;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.cargo.ICargoRepository;
import com.ues.edu.apidecanatoce.services.cargo.ICargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CargoServiceImpl implements ICargoService {

    private final ICargoRepository cargoRepository;

    @Override
    public List<CargosDto> listar(){
        List<Cargo> listCargo = this.cargoRepository.findAll();
        return listCargo.stream().map(Cargo::toDto).toList();
    }

    @Override
    public CargosDto registrar(CargosDto data){
        System.out.println(data.getNombreCargo());
       if (cargoRepository.existsByNombreCargo(data.getNombreCargo())) {
           throw new CustomException(HttpStatus.BAD_REQUEST, "El Cargo ya está registrado");
       }
       return cargoRepository.save(data.toEntityComplete()).toDto();
    }

    @Override
    public CargosDto modificar(UUID id, CargosDto data){
        //CargosDto buscarCargo = leerPorId(id);
        if (this.cargoRepository.existsByNombreCargoAndIdNot(data.getNombreCargo(), id)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "El Cargo ya está registrado");
        }
        //data.setId(id);
        return this.cargoRepository.save(data.toEntityComplete()).toDto();
    }

    @Override
    public CargosDto leerPorId(UUID id) {
        Cargo cargo = cargoRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra el cargo"));
        return cargo.toDto();
    }

    @Override
    public CargosDto leerPorNombre(String nombre) {
        List<Cargo> cargos = cargoRepository.findAll();
        Cargo cargoOb = new Cargo();

        for (Cargo cargo: cargos) {
            if(cargo.getNombreCargo().equals(nombre.toUpperCase())){
                cargoOb = cargo;
            }
        }
        return cargoOb.toDto();
    }

    @Override
    public CargosDto eliminar(UUID id) {
        CargosDto cargo = leerPorId(id);
        cargoRepository.delete(cargo.toEntityComplete());
        return cargo;
    }

    @Override
    public Page<CargosDto> listarConPage(Pageable pageable) {
        Page<Cargo> cargos = cargoRepository.findAll(pageable);
        return cargos.map(Cargo::toDto);
    }

    @Override
    public List<Cargo> findCargoByEstado2(int estado) {
        return this.cargoRepository.findAllByEstado(estado);
    }

    @Override
    public List<Cargo> findAllByNombreCargo(String nombre) {
        return this.cargoRepository.findAllByNombreCargo(nombre);
    }


}
