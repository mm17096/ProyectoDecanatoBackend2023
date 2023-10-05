package com.ues.edu.apidecanatoce.repositorys.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaEmpleadoDto;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IConsultaEmpleadoRepository extends JpaRepository<Empleado, UUID> {
    @Query(value = "SELECT em.nombre AS nombre, em.apellido AS apellido FROM tb_empleado em INNER JOIN tb_cargo AS ca ON em.id_cargo = ca.id WHERE ca.nombre_cargo = 'DECANO'", nativeQuery = true)
    List<ConsultaEmpleadoDto> listarDecanos();
}
