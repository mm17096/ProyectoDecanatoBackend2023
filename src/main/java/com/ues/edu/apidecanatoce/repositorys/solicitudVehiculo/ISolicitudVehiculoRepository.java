package com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo;

import com.ues.edu.apidecanatoce.entities.entradaSalida.Entrada_Salidas;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

public interface ISolicitudVehiculoRepository extends JpaRepository<SolicitudVehiculo, UUID> {
    Page<SolicitudVehiculo> findAllByEstado(int estado, Pageable pageable);
    List<SolicitudVehiculo> findAllByEstado(int estado);
    List<SolicitudVehiculo> findAllByEstadoAndUsuarioEmpleadoDepartamentoNombreOrderByFechaSalidaDesc(int estado,
                                                                                String depto);
    List<SolicitudVehiculo> findByUsuarioCodigoUsuarioOrderByFechaSalidaDesc(String usuario);
    List<SolicitudVehiculo> findByUsuarioCodigoUsuarioAndEstadoOrderByFechaSalidaDesc(String usuario, int estado);
    List<SolicitudVehiculo> findByVehiculoPlaca(String placa);

    @Query("SELECT sv " +
            "FROM SolicitudVehiculo sv " +
            "WHERE sv.estado = :estadoFilter OR sv.estado = :estadoRevision " +
            "ORDER BY sv.fechaSalida DESC ")
    List<SolicitudVehiculo> findByAllSecre(@Param("estadoFilter") int estadoFilter, @Param("estadoRevision") int estadoRevision);


    @Query(" FROM SolicitudVehiculo sv INNER JOIN Vehiculo v ON v.codigoVehiculo = sv.vehiculo.codigoVehiculo LEFT join  Entrada_Salidas  et on et.solicitudvehiculo.codigoSolicitudVehiculo= sv.codigoSolicitudVehiculo WHERE  (DATE_TRUNC('day', sv.fechaSalida) = CURRENT_DATE or et.solicitudvehiculo.codigoSolicitudVehiculo= sv.codigoSolicitudVehiculo) AND (SELECT COUNT(*) FROM Entrada_Salidas c WHERE c.solicitudvehiculo.codigoSolicitudVehiculo=sv.codigoSolicitudVehiculo)<=1")
    List<SolicitudVehiculo> listaporestadofecha();

    @Query("SELECT sv " +
            "FROM SolicitudVehiculo sv " +
            "WHERE (sv.estado = :estadoFilter) OR (sv.estado = :estadoJefe AND sv.usuario.empleado.departamento.nombre = :departamentoFilter) " +
            "ORDER BY sv.fechaSalida DESC")
    List<SolicitudVehiculo> findByAllDec(@Param("estadoFilter") int estadoFilter, @Param("estadoJefe") int estadoJefe, @Param("departamentoFilter") String departamentoFilter);

    @Query(value = "SELECT\n" +
            "\t json_build_object('correo', e.correo, 'nombreCompleto', e.nombre || ' ' || e.apellido) as resultado \n" +
            "FROM\n" +
            "\ttb_empleado e\n" +
            "\tINNER JOIN tb_usuario u ON u.id_empleado = e.codigo_empleado\n" +
            "\tINNER JOIN tb_departamento d ON d.codigo_depto = e.id_departamento \n" +
            "WHERE\n" +
            "\td.nombre = :depto \n" +
            "\tAND ( u.\"role\" = 'JEFE_FINANACIERO' OR u.\"role\" = 'JEFE_DEPTO' OR u.\"role\" = 'DECANO' )",
    nativeQuery = true)
    String obtenerCorreoJefeDepto(@Param("depto") String depto);

    @Query(value = "SELECT\n" +
            "\t json_build_object('correo', e.correo, 'nombreCompleto', e.nombre || ' ' || e.apellido) as resultado \n" +
            "FROM\n" +
            "\ttb_empleado e\n" +
            "\tINNER JOIN tb_usuario u ON e.codigo_empleado = u.id_empleado \n" +
            "WHERE\n" +
            "\tu.codigo_usuario = :id ", nativeQuery = true)
    String obtenerSolicitanteDatos(@Param("id") String id);

    @Query(value = "SELECT\n" +
            "\tjson_build_object('correo', e.correo, 'nombreCompleto', e.nombre || ' ' || e.apellido) as resultado \n" +
            "FROM\n" +
            "\ttb_empleado e\n" +
            "\tINNER JOIN tb_usuario u ON e.codigo_empleado = u.id_empleado \n" +
            "WHERE\n" +
            "\tu.\"role\" = :rol ", nativeQuery = true)
    String obtenerEmailNombreRol(@Param("rol") String rol);

    SolicitudVehiculo findByCodigoSolicitudVehiculo(UUID solicitudvehiculo);
}
