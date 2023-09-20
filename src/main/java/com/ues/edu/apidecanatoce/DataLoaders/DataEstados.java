package com.ues.edu.apidecanatoce.DataLoaders;

import com.ues.edu.apidecanatoce.entities.estados.Estados;
import com.ues.edu.apidecanatoce.repositorys.estados.IEstadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DataEstados implements CommandLineRunner {

    private final IEstadosRepository estadosRepository;

    @Autowired
    public DataEstados(IEstadosRepository estadosRepository) {
        this.estadosRepository = estadosRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Estados estado1 = new Estados(1,"En espera por jefe");
        Estados estado2 = new Estados(2,"Aprobado por jefe");
        Estados estado3 = new Estados(3,"En espera por decano");
        Estados estado4 = new Estados(4,"Aprobada");
        Estados estado5 = new Estados(5,"Asignado");
        Estados estado6 = new Estados(6,"Revisión");
        Estados estado7 = new Estados(7,"Finalizada");
        Estados estado8 = new Estados(8,"Activo");
        Estados estado9 = new Estados(9,"Inactivo");
        Estados estado10 = new Estados(10,"Caducado");
        Estados estado11 = new Estados(11,"Consumido");
        Estados estado12 = new Estados(12,"Devuelto");
        Estados estado13 = new Estados(13,"Gasolinera");
        Estados estado14 = new Estados(14,"UES");
        Estados estado15 = new Estados(15,"Rechazada");
        Estados estado16 = new Estados(16,"Anulada");

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
        estadosRepository.save(estado12);
        estadosRepository.save(estado13);
        estadosRepository.save(estado14);
        estadosRepository.save(estado15);
        estadosRepository.save(estado16);
    }
}

