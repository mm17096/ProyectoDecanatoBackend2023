package com.ues.edu.apidecanatoce.repositorys.solicitudVale;

import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IConsultaValeRepository extends JpaRepository<DetalleAsignacionVale, UUID> {

}
