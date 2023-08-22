package com.ues.edu.apidecanatoce.repositorys;

import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleAsignacionRepository extends JpaRepository<DetalleAsignacionVale, String>{
}
