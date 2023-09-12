package com.ues.edu.apidecanatoce.repositorys.asignacionvale;

import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ISolicitudValeRepository extends JpaRepository<SolicitudVale, UUID> {
}
