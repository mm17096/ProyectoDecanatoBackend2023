package com.ues.edu.apidecanatoce.dataLoaders;

import com.ues.edu.apidecanatoce.entities.cargos.Cargo;
import com.ues.edu.apidecanatoce.repositorys.cargo.ICargoRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Order(1)
public class DataCargos implements CommandLineRunner {

    private final ICargoRepository cargosRepository;


    public DataCargos(ICargoRepository cargoRepository){
        this.cargosRepository = cargoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        UUID id1 =  UUID.fromString("7f43d1ab-e8cb-43ce-9369-d41e14090ef7");  //UUID.randomUUID();
        Cargo cargo1 = new Cargo(id1,"USUARIO COMUN","es el que realiza las solicitudes de vehiculos",8);
        UUID id2 = UUID.fromString("f66f7bcb-9b78-4d51-8fc3-15bf2a3bbf67");
        Cargo cargo2 = new Cargo(id2,"JEFE DEPARTAMENTO","es el que realiza las solicitudes de vehiculos",8);
        UUID id3 = UUID.fromString("a307e06a-ea48-4472-bde9-fa2b8869d889");
        Cargo cargo3 = new Cargo(id3,"SECRETARIO DECANATO","es el que realiza las solicitudes de vehiculos",8);
        UUID id4 = UUID.fromString("95cca893-2946-4de5-af50-81d078e72eba");
        Cargo cargo4 = new Cargo(id4,"DECANO","es el que realiza las solicitudes de vehiculos",8);
        UUID id5 = UUID.fromString("3cc313a2-bde9-472c-bc10-6a58d7bfc4d6");
        Cargo cargo5 = new Cargo(id5,"ASISTENTE FINANCIERA","es el que realiza las solicitudes de vehiculos",8);
        UUID id6 = UUID.fromString("1f2103d4-2e82-464f-a1f0-8de9766be05b");
        Cargo cargo6 = new Cargo(id6,"JEFE FINANCIERO","es el que realiza las solicitudes de vehiculos",8);
        UUID id7 = UUID.fromString("4194392f-8e60-4393-bc28-faf6ad4aa27f");
        Cargo cargo7 = new Cargo(id7,"VIGILANTE","es el que realiza las solicitudes de vehiculos",8);
        UUID id8 = UUID.fromString("9b704f9f-6c94-4014-b1d2-04f307913f80");
        Cargo cargo8 = new Cargo(id8,"ADMINISTRADOR","es el que realiza las solicitudes de vehiculos",8);
        UUID id9 = UUID.fromString("00eb2cb7-5f04-467c-aa0c-ff5c086eb058");
        Cargo cargo9 = new Cargo(id9,"MOTORISTA","encargado de conducir los vehiculos",8);


        if(!cargosRepository.existsByNombreCargo(cargo1.getNombreCargo())){
            cargosRepository.save(cargo1);
        }

        if(!cargosRepository.existsByNombreCargo(cargo2.getNombreCargo())){
            cargosRepository.save(cargo2);
        }

        if(!cargosRepository.existsByNombreCargo(cargo3.getNombreCargo())){
            cargosRepository.save(cargo3);
        }

        if(!cargosRepository.existsByNombreCargo(cargo4.getNombreCargo())){
            cargosRepository.save(cargo4);
        }

        if(!cargosRepository.existsByNombreCargo(cargo5.getNombreCargo())){
            cargosRepository.save(cargo5);
        }

        if(!cargosRepository.existsByNombreCargo(cargo6.getNombreCargo())){
            cargosRepository.save(cargo6);
        }

        if(!cargosRepository.existsByNombreCargo(cargo7.getNombreCargo())){
            cargosRepository.save(cargo7);
        }

        if(!cargosRepository.existsByNombreCargo(cargo8.getNombreCargo())){
            cargosRepository.save(cargo8);
        }

        if(!cargosRepository.existsByNombreCargo(cargo9.getNombreCargo())){
            cargosRepository.save(cargo9);
        }
    }
}
