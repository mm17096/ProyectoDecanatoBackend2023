package com.ues.edu.apidecanatoce.repositorys.logVale;

import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ILogValeRepository extends JpaRepository<LogVale, UUID> {
}