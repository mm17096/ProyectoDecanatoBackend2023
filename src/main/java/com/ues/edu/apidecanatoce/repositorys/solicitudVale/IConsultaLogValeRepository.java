package com.ues.edu.apidecanatoce.repositorys.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaLogValeDto;
import com.ues.edu.apidecanatoce.entities.logVale.LogVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IConsultaLogValeRepository extends JpaRepository<LogVale, UUID> {
    @Query(value = "SELECT lo.id_logvale AS idlogvale, lo.actividad AS actividad, lo.estado_vale AS estadovale, lo.fecha_logvale AS fechalogvale, lo.id_vale AS idvale, (SELECT CONCAT(e.nombre, ' ', e.apellido) FROM tb_usuario AS u INNER JOIN tb_empleado AS e ON u.id_empleado = e.codigo_empleado WHERE u.codigo_usuario = lo.usuario) AS usuario FROM tb_logvale AS lo WHERE lo.id_vale = :id ORDER BY lo.fecha_logvale ASC", nativeQuery = true)
    List<ConsultaLogValeDto> listarLogVale(UUID id);
}
