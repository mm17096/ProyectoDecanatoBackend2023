package com.ues.edu.apidecanatoce.repositorys.empleado;

import com.ues.edu.apidecanatoce.entities.cargos.*;
import com.ues.edu.apidecanatoce.entities.departamentos.Departamento;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, UUID> {
    boolean existsByDui(String dui);
    boolean existsByCargo(Cargo cargo);
    boolean existsByCorreo(String correo);
    boolean existsByLicencia(String licencia);
    boolean existsByDepartamentoAndCargo(Departamento departamento, Cargo cargo);
    boolean existsByDuiAndCodigoEmpleadoNot(String dui, UUID id);
    boolean existsByCorreoAndCodigoEmpleadoNot(String correo, UUID id);
    boolean existsByCargoAndCodigoEmpleadoNot(Cargo cargo, UUID id);
    boolean existsByLicenciaAndCodigoEmpleadoNot(String licencia, UUID id);
    boolean existsByDepartamentoAndCargoAndCodigoEmpleadoNot(Departamento departamento, Cargo cargo, UUID id);

    @Query(value = "SELECT emp.* FROM tb_empleado as emp INNER JOIN tb_cargo cargo ON emp.id_cargo = cargo.\"id\" WHERE emp.estado = 8 AND cargo.nombre_cargo = 'MOTORISTA' AND emp.codigo_empleado NOT IN ( SELECT tsv.codigo_motorista FROM tb_solicitud_vehiculo as tsv WHERE (tsv.fecha_salida BETWEEN :fechaSalida AND :fechaEntrada OR tsv.fecha_entrada  BETWEEN :fechaSalida AND :fechaEntrada OR :fechaSalida BETWEEN tsv.fecha_salida AND tsv.fecha_entrada ) AND tsv.estado in (3,4,5,6)) OR emp.codigo_empleado IN ( SELECT tsv.codigo_motorista FROM tb_solicitud_vehiculo as tsv WHERE (tsv.fecha_salida BETWEEN :fechaSalida AND :fechaEntrada OR tsv.fecha_entrada  BETWEEN :fechaSalida AND :fechaEntrada OR :fechaSalida BETWEEN tsv.fecha_salida AND tsv.fecha_entrada) AND tsv.estado not in (1,2,7) AND tsv.codigo_solicitud_vehiculo in ( SELECT ensal.codigo_solicitud_vehiculo FROM tb_entrada_salida as ensal WHERE ensal.estado = 2 AND ensal.fecha BETWEEN :fechaSalida AND :fechaEntrada) )", nativeQuery = true)
    List<Empleado> listaMotoristas(@Param("fechaSalida") Date fechaSalida, @Param("fechaEntrada") Date fechaEntrada);
}
