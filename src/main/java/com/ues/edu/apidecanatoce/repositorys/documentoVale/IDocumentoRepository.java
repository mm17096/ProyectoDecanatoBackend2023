package com.ues.edu.apidecanatoce.repositorys.documentoVale;

import com.ues.edu.apidecanatoce.entities.documentoVale.Documentovale;
import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IDocumentoRepository extends JpaRepository<Documentovale, UUID> {



    boolean existsByComprobante(String comprobante);
    List<Documentovale> findBySolicitudvale_IdSolicitudVale(UUID id);

}