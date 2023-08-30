package com.ues.edu.apidecanatoce.repositorys.asignacionvale;

import com.ues.edu.apidecanatoce.entities.AsignacionVales.AsignacionVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAsignacionValeRepository extends JpaRepository<AsignacionVale, UUID> {

   /* @Query(value = "SELECT tb_asignacion_vale.codigo_asignacion as idAsignacionVale, concat ( tb_solicitud_vehiculo.objetivo, ', ', tb_solicitud_vehiculo.lugar_mision ) AS mision, tb_vale.codigo_vale AS vale, tb_asignacion_vale.fecha AS fechaAsignacion FROM tb_asignacion_vale INNER JOIN tb_detalle_asignacion_vale ON tb_asignacion_vale.codigo_asignacion = tb_detalle_asignacion_vale.id_asignacion_vale INNER JOIN tb_vale ON tb_detalle_asignacion_vale.valeid = tb_vale.id_vale INNER JOIN tb_solicitud_vale ON tb_asignacion_vale.solicitud_vale_id = tb_solicitud_vale.id_solicitud_vale INNER JOIN tb_solicitud_vehiculo ON tb_solicitud_vale.solicitud_vehiculo_id = tb_solicitud_vehiculo.codigo_solicitud_vehiculo WHERE tb_asignacion_vale.codigo_asignacion =:idAsignacionParam", nativeQuery = true)
    List<IAsignacionValeVistaDto> listarAsignacionValeById(UUID idAsignacionParam);*/
}
