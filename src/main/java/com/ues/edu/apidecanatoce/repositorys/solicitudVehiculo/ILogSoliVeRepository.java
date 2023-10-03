package com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.LogSoliVeDTO;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.LogSolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ILogSoliVeRepository extends JpaRepository<LogSolicitudVehiculo, UUID> {
    List<LogSolicitudVehiculo> findBySoliVe(SolicitudVehiculo solicitudVehiculo);
}
