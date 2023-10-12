package com.ues.edu.apidecanatoce.repositorys.logVale;

import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ILogValeRepository extends JpaRepository<LogVale, UUID> {

    List<LogVale> findByEstadoValeAndFechaLogValeBetween(int estado, LocalDateTime startDate, LocalDateTime endDate);
}