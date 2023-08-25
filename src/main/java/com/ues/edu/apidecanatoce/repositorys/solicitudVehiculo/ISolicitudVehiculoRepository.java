package com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo;

import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ISolicitudVehiculoRepository extends JpaRepository<SolicitudVehiculo, UUID> {
    List<SolicitudVehiculo> findAllByEstado(int estado);
}
