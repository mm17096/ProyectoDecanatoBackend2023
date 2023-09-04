package com.ues.edu.apidecanatoce.repositorys;

import com.ues.edu.apidecanatoce.entities.AsignacionVales.AsignacionVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAsignacionValeRepository extends JpaRepository<AsignacionVale, UUID> {
}