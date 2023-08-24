package com.ues.edu.apidecanatoce.repositorys;

import com.ues.edu.apidecanatoce.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDeptopRepo extends JpaRepository<Departamento,Integer> {

    List<Departamento> findAllByEstado(int estado);

}
