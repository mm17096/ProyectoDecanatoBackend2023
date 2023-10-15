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
            SELECT * FROM tb_vehiculo as v WHERE v.codigo_vehiculo
             IN (\s
            select s.codigo_vehiculo FROM tb_solicitud_vehiculo as s
            --fecha salida -- fecha entrada
            WHERE (s.fecha_salida BETWEEN :fechaSalida and :fechaEntrada OR\s
            			s.fecha_entrada between :fechaSalida and :fechaEntrada)
            and s.codigo_solicitud_vehiculo IN (select tsv.codigo_solicitud_vehiculo from tb_entrada_salida as tsv
            where tsv.estado = 2)) and v.clase = :claseName
            OR v.codigo_vehiculo NOT IN(select car.codigo_vehiculo from tb_solicitud_vehiculo as car
            --fecha salida -- fecha entrada
            WHERE (car.fecha_salida BETWEEN :fechaSalida and :fechaEntrada OR\s
            			car.fecha_entrada between :fechaSalida and :fechaEntrada)) and v.clase = :claseName
            """,nativeQuery = true)
    List<Vehiculo> buscarDisponibilidad(@Param("claseName") String claseName,
                                        @Param("fechaSalida") Date fechaSalida,
                                        @Param("fechaEntrada") Date fechaEntrada);

}
