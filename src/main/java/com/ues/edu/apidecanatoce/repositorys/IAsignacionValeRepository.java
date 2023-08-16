package com.ues.edu.apidecanatoce.repositorys;

import com.ues.edu.apidecanatoce.entities.AsignacionVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAsignacionValeRepository extends JpaRepository<AsignacionVale, Integer> {
}
