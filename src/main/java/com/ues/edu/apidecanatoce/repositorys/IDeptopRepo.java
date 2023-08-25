package com.ues.edu.apidecanatoce.repositorys;

import com.ues.edu.apidecanatoce.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IDeptopRepo extends JpaRepository<Departamento, UUID> {
}
