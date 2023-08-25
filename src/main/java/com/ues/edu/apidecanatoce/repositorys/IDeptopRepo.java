package com.ues.edu.apidecanatoce.repositorys;

import com.ues.edu.apidecanatoce.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeptopRepo extends JpaRepository<Departamento, String> {
}
