package com.ues.edu.apidecanatoce.repositorys.solicitudVale;


import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaValeGDto;
import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface IConsultaValeGRepository extends JpaRepository<DetalleAsignacionVale, UUID> {
    @Query(value = "SELECT dav.id_detalle_asignacion_vale AS iddetalleasignacionvale, av.estado AS estadoav, av.fecha AS fecha, sv.cantidad_vale AS cantidadvale, \n" +
            "sv.estado AS estadosv, sv.estado_entrada AS estadoentrada,\n" +
            "v.correlativo AS correlativo, v.estado AS estadovale, v.fecha_vencimiento \n" +
            "AS fechavencimiento, v.valor AS valor\n" +
            "FROM tb_detalle_asignacion_vale dav\n" +
            "FULL JOIN tb_asignacion_vale as av ON dav.id_asignacion_vale = av.codigo_asignacion\n" +
            "FULL JOIN tb_solicitud_vale as sv ON av.solicitud_vale_id = sv.id_solicitud_vale\n" +
            "INNER JOIN tb_vale AS v ON dav.valeid = v.id_vale\n" +
            "WHERE av.fecha >=:fechaI AND av.fecha <= :fechaF\n" +
            "ORDER BY av.fecha desc", nativeQuery = true)
    List<ConsultaValeGDto> listarVales(LocalDate fechaI, LocalDate fechaF);
}
