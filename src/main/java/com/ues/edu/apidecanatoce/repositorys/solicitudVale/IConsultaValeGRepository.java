package com.ues.edu.apidecanatoce.repositorys.solicitudVale;


import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.*;
import com.ues.edu.apidecanatoce.entities.AsignacionVales.DetalleAsignacionVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface IConsultaValeGRepository extends JpaRepository<DetalleAsignacionVale, UUID> {
    @Query(value = "SELECT sv.solicitud_vehiculo_id AS solicitudvehiculoid, dav.id_detalle_asignacion_vale AS iddetalleasignacionvale," +
            "dav.id_asignacion_vale AS idasignacionvale, dav.valeid AS valeid," +
            "av.estado AS estadoav, av.fecha AS fecha, sv.cantidad_vale AS cantidadvale, \n" +
            "sv.estado AS estadosv, sv.estado_entrada AS estadoentrada,\n" +
            "v.correlativo AS correlativo, v.estado AS estadovale, v.fecha_vencimiento \n" +
            "AS fechavencimiento, v.valor AS valor\n" +
            "FROM tb_detalle_asignacion_vale dav\n" +
            "FULL JOIN tb_asignacion_vale as av ON dav.id_asignacion_vale = av.codigo_asignacion\n" +
            "FULL JOIN tb_solicitud_vale as sv ON av.solicitud_vale_id = sv.id_solicitud_vale\n" +
            "INNER JOIN tb_vale AS v ON dav.valeid = v.id_vale\n" +
            "WHERE av.fecha >=:fechaI AND av.fecha <= :fechaF\n" +
            "ORDER BY av.fecha, sv.cantidad_vale desc", nativeQuery = true)
    List<ConsultaValeGDto> listarVales(LocalDate fechaI, LocalDate fechaF);
    @Query(value = "SELECT sv.cantidad_vale AS cantidadvale, \n" +
            "v.correlativo AS correlativo, \n" +
            "v.estado AS estadovale, \n" +
            "v.fecha_vencimiento AS fechavencimiento, \n" +
            "v.valor AS valor,\n" +
            "sv.solicitud_vehiculo_id AS solicitudvehiculoid,\n" +
            "dav.valeid AS valeid\n" +
            "FROM tb_detalle_asignacion_vale dav\n" +
            "FULL JOIN tb_asignacion_vale as av ON dav.id_asignacion_vale = av.codigo_asignacion\n" +
            "FULL JOIN tb_solicitud_vale as sv ON av.solicitud_vale_id = sv.id_solicitud_vale\n" +
            "FULL JOIN tb_vale AS v ON dav.valeid = v.id_vale\n" +
            "WHERE sv.solicitud_vehiculo_id = :id\n" +
            "ORDER BY v.correlativo ASC", nativeQuery = true)
    List<ConsultaCantidadValesDelAlDto> listarValesDelAl(UUID id);

    @Query(value = "SELECT v.codigo_compra AS codigocompra FROM tb_detalle_asignacion_vale dav FULL JOIN tb_asignacion_vale as av ON dav.id_asignacion_vale = av.codigo_asignacion FULL JOIN tb_solicitud_vale as sv ON av.solicitud_vale_id = sv.id_solicitud_vale FULL JOIN tb_vale AS v ON dav.valeid = v.id_vale WHERE sv.solicitud_vehiculo_id = :id", nativeQuery = true)
    List<ConsultaIdCompraDto> listarIdCompra(UUID id);
    @Query(value = "SELECT v.id_vale AS idvale, v.correlativo AS correlativo, v.estado AS estado, v.fecha_vencimiento AS fechavencimiento, v.valor AS valor, v.codigo_compra AS codigocompra FROM tb_detalle_asignacion_vale dav FULL JOIN tb_asignacion_vale as av ON dav.id_asignacion_vale = av.codigo_asignacion FULL JOIN tb_solicitud_vale as sv ON av.solicitud_vale_id = sv.id_solicitud_vale FULL JOIN tb_vale AS v ON dav.valeid = v.id_vale WHERE sv.solicitud_vehiculo_id = :id ORDER BY v.correlativo ASC", nativeQuery = true)
    List<ConsultaIdValeDto> listarIdVale(UUID id);


}
