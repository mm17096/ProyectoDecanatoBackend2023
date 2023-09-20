package com.ues.edu.apidecanatoce.DataLoaders;

import com.ues.edu.apidecanatoce.entities.cargos.Cargo;
import com.ues.edu.apidecanatoce.repositorys.cargo.ICargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Order(1)
public class DataCargos implements CommandLineRunner {

    private final ICargoRepository cargosRepository;

    @Autowired
    public DataCargos(ICargoRepository cargoRepository){
        this.cargosRepository = cargoRepository;
    }

    @Override
    public void run(String ... arg) throws Exception{

        UUID id1 = UUID.randomUUID();
        Cargo cargo1 = new Cargo(id1,"USUARIO COMUN","es el que realiza las solicitudes de vehiculos",8);
        UUID id2 = UUID.randomUUID();
        Cargo cargo2 = new Cargo(id2,"JEFE DEPARTAMENTO","es el que realiza las solicitudes de vehiculos",8);
        UUID id3 = UUID.randomUUID();
        Cargo cargo3 = new Cargo(id3,"SECRETARIO DECANATO","es el que realiza las solicitudes de vehiculos",8);
        UUID id4 = UUID.randomUUID();
        Cargo cargo4 = new Cargo(id4,"DECANATO","es el que realiza las solicitudes de vehiculos",8);
        UUID id5 = UUID.randomUUID();
        Cargo cargo5 = new Cargo(id5,"ASISTENTE FINANCIERA","es el que realiza las solicitudes de vehiculos",8);
        UUID id6 = UUID.randomUUID();
        Cargo cargo6 = new Cargo(id6,"JEFE FINANCIERO","es el que realiza las solicitudes de vehiculos",8);
        UUID id7 = UUID.randomUUID();
        Cargo cargo7 = new Cargo(id7,"VIGILANTE","es el que realiza las solicitudes de vehiculos",8);
        UUID id8 = UUID.randomUUID();
        Cargo cargo8 = new Cargo(id8,"ADMINISTRADOR","es el que realiza las solicitudes de vehiculos",8);

        cargosRepository.save(cargo1);
        cargosRepository.save(cargo2);
        cargosRepository.save(cargo3);
        cargosRepository.save(cargo4);
        cargosRepository.save(cargo5);
        cargosRepository.save(cargo6);
        cargosRepository.save(cargo7);
        cargosRepository.save(cargo8);
    }


}
