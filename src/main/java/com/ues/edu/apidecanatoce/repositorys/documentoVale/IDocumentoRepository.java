package com.ues.edu.apidecanatoce.repositorys.documentoVale;

import com.ues.edu.apidecanatoce.entities.documentoVale.Documentovale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDocumentoRepository extends JpaRepository<Documentovale, UUID> {


    //boolean existsComprobante(String comprobante);
    boolean existsByComprobante(String comprobante);

    //boolean existsComprobante(String comprobante);
}