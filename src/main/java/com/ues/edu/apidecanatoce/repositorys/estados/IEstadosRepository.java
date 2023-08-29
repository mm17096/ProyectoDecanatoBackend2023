package com.ues.edu.apidecanatoce.repositorys.estados;

import com.ues.edu.apidecanatoce.entities.Estados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstadosRepository extends JpaRepository<Estados, Integer> {
}
