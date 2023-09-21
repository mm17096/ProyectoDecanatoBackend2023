package com.ues.edu.apidecanatoce.repositorys.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.IValeAsignarDto;
import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IConsultaValeRepository extends JpaRepository<DetalleAsignacionVale, UUID> {

}
