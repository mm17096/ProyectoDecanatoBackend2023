package com.ues.edu.apidecanatoce.repositorys.solicitudVale;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaLogSoliVeDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaLogSoliVeIDDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaUsuarioDto;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.LogSolicitudVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IConsultaLogSoliVehiRepository extends JpaRepository<LogSolicitudVehiculo, UUID> {
    @Query(value = "SELECT ls.actividad AS actividad, ls.estado_solive AS estadosolive, ls.fecha_logsolive AS fechalogsolive, ls.usuario AS usuario, ls.cargo AS cargo FROM tb_log_solicitud_vehiculo ls INNER JOIN tb_solicitud_vehiculo AS sv ON ls.codigo_solive=sv.codigo_solicitud_vehiculo WHERE ls.codigo_solive =:id ORDER BY ls.fecha_logsolive ASC", nativeQuery = true)
    List<ConsultaLogSoliVeDto> listarLogSoliVehi(UUID id);

    @Query(value = "SELECT ls.actividad AS actividad, ls.estado_solive AS estadosolive, ls.fecha_logsolive AS fechalogsolive, ls.usuario AS usuario, ls.cargo AS cargo FROM tb_log_solicitud_vehiculo ls INNER JOIN tb_solicitud_vehiculo AS sv ON ls.codigo_solive=sv.codigo_solicitud_vehiculo WHERE ls.codigo_solivale =:id ORDER BY ls.fecha_logsolive ASC", nativeQuery = true)
    List<ConsultaLogSoliVeIDDto> listarLogSoliVehiID(UUID id);

    @Query(value = "SELECT ls.usuario AS usuario, ls.cargo AS cargo FROM tb_log_solicitud_vehiculo AS ls INNER JOIN tb_solicitud_vehiculo AS sv ON ls.codigo_solive = sv.codigo_solicitud_vehiculo WHERE CAST(ls.fecha_logsolive AS DATE) >= :fechaI  AND CAST(ls.fecha_logsolive AS DATE) <= :fechaF AND ls.cargo = 'DECANO'", nativeQuery = true)
    List<ConsultaUsuarioDto> listarUsuario(LocalDate fechaI, LocalDate fechaF);
}
