package com.ues.edu.apidecanatoce.repositorys;

import com.ues.edu.apidecanatoce.dtos.EmpleadoTablaDTO;
import com.ues.edu.apidecanatoce.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado,Integer> {
    @Query("from Empleado m where (LOWER(m.dui) like %:filtro%) OR (LOWER(m.nombre) like %:filtro%) OR (LOWER(m.apellido) like %:filtro%)")
    List<Empleado> buscarEmpleado(@Param("filtro") String filtro);
}
