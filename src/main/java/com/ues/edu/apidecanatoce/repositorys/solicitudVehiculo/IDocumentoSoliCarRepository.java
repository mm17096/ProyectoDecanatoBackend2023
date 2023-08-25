package com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo;

import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.DocumentoSoliCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDocumentoSoliCarRepository extends JpaRepository<DocumentoSoliCar, UUID> {

}
