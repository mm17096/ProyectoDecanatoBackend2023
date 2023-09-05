package com.ues.edu.apidecanatoce.repositorys.documentoVale;
import com.ues.edu.apidecanatoce.entities.documentoVale.Documentovale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Documentosrepository extends JpaRepository<Documentovale, UUID> {

}
