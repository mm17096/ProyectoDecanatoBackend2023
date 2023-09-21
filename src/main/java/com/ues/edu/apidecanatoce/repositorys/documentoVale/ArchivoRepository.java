package com.ues.edu.apidecanatoce.repositorys.documentoVale;

import com.ues.edu.apidecanatoce.entities.documentoVale.Documentovale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArchivoRepository extends JpaRepository<Documentovale, UUID> {
    Optional<Documentovale> findByFoto(String filename);
}
