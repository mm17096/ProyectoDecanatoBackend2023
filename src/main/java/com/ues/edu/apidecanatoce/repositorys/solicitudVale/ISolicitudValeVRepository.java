package com.ues.edu.apidecanatoce.repositorys.solicitudVale;

import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ISolicitudValeVRepository extends JpaRepository<SolicitudVale, UUID> {
       //Page<SolicitudVale> findAll(Pageable pageable);
    SolicitudVale  findBySolicitudVehiculo_CodigoSolicitudVehiculo(UUID codigosolicitudvehiculo);
}
