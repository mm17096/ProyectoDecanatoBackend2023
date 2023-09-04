package com.ues.edu.apidecanatoce.repositorys.asignacionvale;

import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDetalleAsignacionRepository extends JpaRepository<DetalleAsignacionVale, UUID>{
}
