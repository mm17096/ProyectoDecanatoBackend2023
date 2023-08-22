package com.ues.edu.apidecanatoce.DataLoaders;

import com.ues.edu.apidecanatoce.entities.Estados;
import com.ues.edu.apidecanatoce.repositorys.EstadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DataEstados implements CommandLineRunner {

    private final EstadosRepository estadosRepository;

    @Autowired
    public DataEstados(EstadosRepository estadosRepository) {
        this.estadosRepository = estadosRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Estados estado1 = new Estados(1,"En espera");
        Estados estado2 = new Estados(2,"Por aprobar");
        Estados estado3 = new Estados(3,"Aprobada");
        Estados estado4 = new Estados(4,"Asignado");
        Estados estado5 = new Estados(5,"Revision");
        Estados estado6 = new Estados(6,"Finalizada");
        Estados estado7 = new Estados(7,"Activo");
        Estados estado8 = new Estados(8,"Inactivo");
        Estados estado9 = new Estados(9,"Caducado");
        Estados estado10 = new Estados(10,"Consumido");
        Estados estado11 = new Estados(11,"Devuelto");

        estadosRepository.save(estado1);
        estadosRepository.save(estado2);
        estadosRepository.save(estado3);
        estadosRepository.save(estado4);
        estadosRepository.save(estado5);
        estadosRepository.save(estado6);
        estadosRepository.save(estado7);
        estadosRepository.save(estado8);
        estadosRepository.save(estado9);
        estadosRepository.save(estado10);
        estadosRepository.save(estado11);
    }
}

