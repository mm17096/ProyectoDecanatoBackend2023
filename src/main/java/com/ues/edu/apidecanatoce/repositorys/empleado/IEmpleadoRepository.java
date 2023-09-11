package com.ues.edu.apidecanatoce.repositorys.empleado;

import com.ues.edu.apidecanatoce.entities.cargos.*;
import com.ues.edu.apidecanatoce.entities.departamentos.Departamento;
import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

}
