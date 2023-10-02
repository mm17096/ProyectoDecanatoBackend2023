package com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo;

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
    List<SolicitudVehiculo> findAllByEstadoAndUsuarioEmpleadoDepartamentoNombre(int estado,
                                                                                String depto);
    List<SolicitudVehiculo> findByUsuarioCodigoUsuarioOrderByFechaSalidaDesc(String usuario);
    List<SolicitudVehiculo> findByUsuarioCodigoUsuarioAndEstadoOrderByFechaSalidaDesc(String usuario, int estado);
    List<SolicitudVehiculo> findByVehiculoPlaca(String placa);

    @Query("SELECT sv " +
            "FROM SolicitudVehiculo sv " +
            "WHERE sv.estado = :estadoFilter OR sv.estado = :estadoRevision")
    List<SolicitudVehiculo> findByAllSecreDec(@Param("estadoFilter") int estadoFilter, @Param("estadoRevision") int estadoRevision);

}
