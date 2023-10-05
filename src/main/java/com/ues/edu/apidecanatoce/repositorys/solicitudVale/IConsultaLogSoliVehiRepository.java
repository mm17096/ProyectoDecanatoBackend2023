package com.ues.edu.apidecanatoce.repositorys.solicitudVale;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaLogSoliVeDto;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.LogSolicitudVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IConsultaLogSoliVehiRepository extends JpaRepository<LogSolicitudVehiculo, UUID> {
    @Query(value = "SELECT ls.actividad AS actividad, ls.estado_solive AS estadosolive, ls.fecha_logsolive AS fechalogsolive, ls.usuario AS usuario FROM tb_log_solicitud_vehiculo ls INNER JOIN tb_solicitud_vehiculo AS sv ON ls.codigo_solive=sv.codigo_solicitud_vehiculo WHERE sv.codigo_solicitud_vehiculo =:id ORDER BY ls.fecha_logsolive ASC", nativeQuery = true)
    List<ConsultaLogSoliVeDto> listarLogSoliVehi(UUID id);
}
