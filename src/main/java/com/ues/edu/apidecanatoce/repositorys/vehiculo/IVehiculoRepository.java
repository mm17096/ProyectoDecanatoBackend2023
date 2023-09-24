package com.ues.edu.apidecanatoce.repositorys.vehiculo;

import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, UUID> {
    boolean existsByPlacaIgnoreCase(String placa);
    boolean existsByPlacaAndCodigoVehiculoNot(String placa, UUID codigoVehiculo);
    List<Vehiculo> findByPlacaIgnoreCase(String mio);
    List<Vehiculo> findByClaseIgnoreCase(String clase);
    @Query(value = "SELECT * FROM tb_vehiculo v WHERE v.codigo_vehiculo NOT IN ( select  s.codigo_vehiculo \n" +
            "    FROM tb_solicitud_vehiculo s \n" +
            "    --fecha salida\n" +
            "    WHERE (:fechaSalida between s.fecha_salida  and s.fecha_entrada )\n" +
            "    --fecha entrada\n" +
            "    OR (:fechaEntrada between s.fecha_salida  and s.fecha_entrada )\n" +
            ") and v.clase =:claseName",nativeQuery = true)
    List<Vehiculo> buscarDisponibilidad(@Param("claseName") String claseName,
                                        @Param("fechaSalida") Date fechaSalida,
                                        @Param("fechaEntrada") Date fechaEntrada);

}
