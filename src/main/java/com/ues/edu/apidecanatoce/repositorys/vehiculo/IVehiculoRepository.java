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
    @Query(value = "select tv.clase  from tb_vehiculo tv group by tv.clase", nativeQuery = true)
    List<String> showByClassFiltrar();
    @Query(value = """
            SELECT * FROM tb_vehiculo as v WHERE v.codigo_vehiculo\s
            NOT IN ( select s.codigo_vehiculo FROM tb_solicitud_vehiculo s\s
            --fecha salida
            WHERE (:fechaSalida between s.fecha_salida  and s.fecha_entrada)
            and s.codigo_solicitud_vehiculo IN (select tsv.solicitud_vehiculo_id from tb_solicitud_vale as tsv
            where tsv.estado_entrada != 2))
            and v.codigo_vehiculo NOT IN(select car.codigo_vehiculo from tb_solicitud_vehiculo as car
            --fecha salida
            WHERE (:fechaSalida between car.fecha_salida  and car.fecha_entrada)\s
            and car.codigo_solicitud_vehiculo NOT IN (select tsv.solicitud_vehiculo_id from tb_solicitud_vale as tsv)
            and car.estado !=15)
            and v.clase =:claseName
            """,nativeQuery = true)
    List<Vehiculo> buscarDisponibilidad(@Param("claseName") String claseName,
                                        @Param("fechaSalida") Date fechaSalida);

}
