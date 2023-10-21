package com.ues.edu.apidecanatoce.repositorys.entradaSalida;

import com.ues.edu.apidecanatoce.dtos.entradasalidaDto.CorreosESDto;
import com.ues.edu.apidecanatoce.entities.entradaSalida.Entrada_Salidas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EntradasalidaRepository extends JpaRepository<Entrada_Salidas, UUID> {
    Entrada_Salidas findByEstadoAndSolicitudvehiculo_CodigoSolicitudVehiculo(int dato,UUID id);

    @Query("FROM Entrada_Salidas es INNER JOIN SolicitudVehiculo sv on sv.codigoSolicitudVehiculo=es.solicitudvehiculo.codigoSolicitudVehiculo  where es.estado=1 AND sv.codigoSolicitudVehiculo=:filtro")
    Entrada_Salidas mostrandokilometraje(@Param("filtro") UUID filtro);

    @Query(value = "SELECT us.codigo_usuario AS codigoUsuario, us.role AS rol, concat ( e.nombre, ' ', e.apellido ) AS nombre, e.correo AS correo FROM tb_usuario AS us INNER JOIN tb_empleado AS e ON us.id_empleado = e.codigo_empleado WHERE us.role = 'JEFE_FINANACIERO' OR us.role = 'ASIS_FINANCIERO'", nativeQuery = true)
    List<CorreosESDto> correos();

}
