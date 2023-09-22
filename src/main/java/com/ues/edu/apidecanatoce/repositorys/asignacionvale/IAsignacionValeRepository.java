package com.ues.edu.apidecanatoce.repositorys.asignacionvale;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.IValeAsignarDto;
import com.ues.edu.apidecanatoce.entities.AsignacionVales.AsignacionVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAsignacionValeRepository extends JpaRepository<AsignacionVale, UUID> {


    @Query(value = "SELECT codigo_asignacion FROM tb_asignacion_vale WHERE solicitud_vale_id =:solicitudID ORDER BY codigo_asignacion DESC LIMIT 1", nativeQuery = true)
    UUID findTopByOrderByIdDesc(UUID solicitudID);

    @Query(value = "SELECT tb_v.id_vale AS idVale, tb_v.correlativo AS correlativoVale, tb_v.valor AS valorVale FROM tb_vale AS tb_v WHERE tb_v.estado = 8 ORDER BY tb_v.correlativo ASC LIMIT :cantidadVales", nativeQuery = true)
    List<IValeAsignarDto> listarValesAsignar(int cantidadVales);

    @Query(value = "SELECT count(*) AS valesDisponibles FROM tb_vale AS tb_v WHERE tb_v.estado = 8", nativeQuery = true)
    Integer valesDisponibles();

    @Query(value = "SELECT SUM ( tv.valor ) AS totalDineroVales FROM tb_vale AS tv WHERE tv.id_vale IN ( SELECT id_vale FROM tb_vale LIMIT :cantidadVales )", nativeQuery = true)
    Double totalDineroVales(int cantidadVales);

    @Query(value = "SELECT tb_sv.id_solicitud_vale FROM tb_solicitud_vale AS tb_sv WHERE tb_sv.solicitud_vehiculo_id =:codigoSolicitudVehiculo", nativeQuery = true)
    UUID findByIDSolicitudVale(UUID codigoSolicitudVehiculo);

    @Query(value = "SELECT tb_asv.codigo_asignacion FROM tb_asignacion_vale as tb_asv WHERE tb_asv.solicitud_vale_id =:codigoAsignacionVale", nativeQuery = true)
    UUID findByIdAsignacionVale(UUID codigoAsignacionVale);



    @Query(value = "SELECT tb_sv.solicitud_vehiculo_id FROM tb_solicitud_vale AS tb_sv WHERE tb_sv.id_solicitud_vale =:codigoSolicituVehiculo", nativeQuery = true)
    UUID findByIdSolicitudVehiculo(UUID codigoSolicituVehiculo);

    @Query(value = "SELECT * FROM tb_detalle_asignacion_vale AS tb_dav WHERE tb_dav.valeid =:codigoVale", nativeQuery = true)
    UUID findDetalleAsigancionVale(UUID codigoVale);

}
