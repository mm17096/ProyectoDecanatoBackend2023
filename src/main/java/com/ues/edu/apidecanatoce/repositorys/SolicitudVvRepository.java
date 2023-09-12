package com.ues.edu.apidecanatoce.repositorys;

import com.ues.edu.apidecanatoce.dtos.SolicitudVvDTO;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudVvRepository extends JpaRepository<SolicitudVehiculo, Long> {
    @Query(value = "SELECT sve.cantidad_personas as contidadpersonas, \n" +
            "sve.lugar_mision as lugarmision,\n" +
            "sve.direccion as direccion,\n" +
            "sve.objetivo as objetivo,\n" +
            "sve.dui_motorista as duimotorista,\n" +
            "sve.fecha_salida as fechaSalidad,\n" +
            "sve.estado AS estado,\n" +
            "v.tipo as tipo,\n" +
            "v.modelo as modelo,\n" +
            "concat(e.nombre, ' ', e.apellido) as nombresolicitante,\n" +
            "concat(em.nombre, ' ', em.apellido) as nombremotorista\n" +
            "FROM tb_solicitud_vehiculo sve\n" +
            "INNER JOIN tb_vehiculo v on sve.codigo_vehiculo = v.codigo_vehiculo\n" +
            "INNER JOIN tb_usuario u ON sve.codigo_usuario = u.codigo_usuario\n" +
            "INNER JOIN tb_empleado e ON u.id_empleado = e.dui\n" +
            "INNER JOIN tb_empleado em ON sve.dui_motorista = em.dui", nativeQuery = true)
        // @Query(value = "SELECT sve.direccion as direccion FROM tb_solicitud_vehiculo sve", nativeQuery = true)
    List<SolicitudVvDTO> findPartialInfo();
}
