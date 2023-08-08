package com.ues.edu.apidecanatoce.entities;

import com.ues.edu.apidecanatoce.repositorys.EstadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataEstados implements CommandLineRunner {

    private final EstadosRepository estadosRepository;

    @Autowired
    public DataEstados(EstadosRepository estadosRepository) {
        this.estadosRepository = estadosRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Aquí puedes agregar código para insertar datos en la tabla
        Estados estado1 = new Estados(0,"Estado 1");
        Estados estado2 = new Estados(0,"Estado 2");

        estadosRepository.save(estado1);
        estadosRepository.save(estado2);
    }
}

