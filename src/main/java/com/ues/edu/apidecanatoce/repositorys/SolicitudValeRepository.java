package com.ues.edu.apidecanatoce.repositorys;

import com.ues.edu.apidecanatoce.entities.SolicitudVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SolicitudValeRepository extends JpaRepository<SolicitudVale, Long> {
    //Collection<SolicitudVale> findAll();
}

