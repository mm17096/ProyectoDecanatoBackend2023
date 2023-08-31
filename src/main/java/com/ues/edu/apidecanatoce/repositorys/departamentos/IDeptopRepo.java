package com.ues.edu.apidecanatoce.repositorys.departamentos;

import com.ues.edu.apidecanatoce.entities.departamentos.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IDeptopRepo extends JpaRepository<Departamento, UUID> {
    List<Departamento> findAllByEstado(int estado);

    List<Departamento> findAllByNombre(String estado);

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndCodigoDeptoNot(String nombre, UUID codigoDepto);

}
