package com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo;

import com.ues.edu.apidecanatoce.dtos.solicitudVehiculo.LogSoliVeDTO;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.LogSolicitudVehiculo;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ILogSoliVeRepository extends JpaRepository<LogSolicitudVehiculo, UUID> {

    @Query(value = "\tSELECT\n" +
            "\t*\n" +
            "\tFROM\n" +
            "\ttb_log_solicitud_vehiculo  logv\n" +
            "\tWHERE logv.codigo_solive = :solicitudVehiculoId \n" +
            "\tOR logv.codigo_solivale = :solicitudValeId \n" +
            "\tORDER BY logv.fecha_logsolive ASC", nativeQuery = true)
    List<LogSolicitudVehiculo> obtenerLogSoliM(@Param("solicitudVehiculoId") UUID solicitudVehiculoId,
                                               @Param("solicitudValeId") UUID solicitudValeId);


    @Query("SELECT sva FROM SolicitudVale sva WHERE sva.solicitudVehiculo.codigoSolicitudVehiculo = :codigoSolicitudVehiculo")
    SolicitudVale findBySoliVa(@Param("codigoSolicitudVehiculo") UUID codigoSolicitudVehiculo);



}
