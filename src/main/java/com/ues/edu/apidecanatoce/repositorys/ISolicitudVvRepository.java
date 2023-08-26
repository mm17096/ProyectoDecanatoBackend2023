package com.ues.edu.apidecanatoce.repositorys;

import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISolicitudVvRepository extends JpaRepository<SolicitudVehiculo, Integer> {
    List<SolicitudVehiculo> findAllByEstado(int estado);
}
