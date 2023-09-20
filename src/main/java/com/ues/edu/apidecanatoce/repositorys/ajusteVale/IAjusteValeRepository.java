package com.ues.edu.apidecanatoce.repositorys.ajusteVale;

import com.ues.edu.apidecanatoce.entities.ajustesVale.AjusteVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAjusteValeRepository extends JpaRepository<AjusteVale, UUID> {
}