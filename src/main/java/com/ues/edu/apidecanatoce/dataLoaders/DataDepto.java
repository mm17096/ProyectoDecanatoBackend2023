package com.ues.edu.apidecanatoce.dataLoaders;

import com.ues.edu.apidecanatoce.entities.departamentos.Departamento;
import com.ues.edu.apidecanatoce.repositorys.departamentos.IDeptopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Order(1)
public class DataDepto implements CommandLineRunner {

    private final IDeptopRepo iDeptopRepo;

    @Autowired
    public DataDepto(IDeptopRepo iDeptopRepo){
        this.iDeptopRepo = iDeptopRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        UUID id = UUID.fromString("0107bbee-87cf-42e3-8155-0c62edd2a1bd");
        Departamento obj = new Departamento(id,"UTI","Unidad de tecnologia de la información","Administrativo",8);

        if(!iDeptopRepo.existsByNombre(obj.getNombre())){
            iDeptopRepo.save(obj);
        }
    }


}
