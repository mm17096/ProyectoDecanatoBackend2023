package com.ues.edu.apidecanatoce.repositorys.asignacionvale;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.vales.ISolicitudValeFiltradasDto;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ISolicitudValeRepository extends JpaRepository<SolicitudVale, UUID> {
    @Query(value = "SELECT sv.id_solicitud_vale AS codigoSolicitudVale, sv.cantidad_vale AS cantidadVales, sv.estado AS estadoSolicitud, sv.estado_entrada AS estadoEntradaSolicitudVale, sv.solicitud_vehiculo_id AS codigoSolictudVehiculo, sv.observaiones AS observacionesSolicitudVale, v.modelo AS modeloVehiculo, v.placa AS placaVehiculo, sve.codigo_solicitud_vehiculo AS codigoSolicitudVehiculos, sve.cantidad_personas AS cantidadPersonas, sve.direccion AS direccionMision, concat ( sve.objetivo, ', ', sve.lugar_mision ) AS mision, sve.codigo_motorista AS duiMotorista, sve.fecha_salida AS fechaSalida, sve.fecha_entrada AS fechaEntrada, sve.estado AS estadoSolicitudVehiculo, sve.fecha_solicitud AS fechaSolicitud, sve.unidad AS unidadSolicitante, concat ( e.nombre, ' ', e.apellido ) AS nombreSolicitante, concat ( em.nombre, ' ', em.apellido ) AS nombreMotorista, em.codigo_empleado AS codigoEmpleado, em.correo AS correoEmpleado FROM tb_solicitud_vale AS sv INNER JOIN tb_solicitud_vehiculo AS sve ON sv.solicitud_vehiculo_id = sve.codigo_solicitud_vehiculo INNER JOIN tb_vehiculo AS v ON sve.codigo_vehiculo = v.codigo_vehiculo INNER JOIN tb_usuario AS us ON sve.codigo_usuario = us.codigo_usuario INNER JOIN tb_empleado AS em ON sve.codigo_motorista = em.codigo_empleado INNER JOIN tb_empleado AS e ON us.id_empleado = e.codigo_empleado WHERE sv.estado =:estado ORDER BY sve.fecha_salida ASC", nativeQuery = true)
    List<ISolicitudValeFiltradasDto> findSolicitudValeByEstado(int estado);

}
