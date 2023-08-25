package com.ues.edu.apidecanatoce.repositorys.compras;

import com.ues.edu.apidecanatoce.entities.compras.Vale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IValeRepository extends JpaRepository<Vale, UUID> {

    boolean existsByCodigoVale(long codigoVale);

    boolean existsByCodigoValeAndIdNot(long codigoVale, UUID id);
}