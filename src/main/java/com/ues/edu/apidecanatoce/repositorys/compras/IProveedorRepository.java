package com.ues.edu.apidecanatoce.repositorys.compras;

import com.ues.edu.apidecanatoce.entities.compras.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProveedorRepository extends JpaRepository<Proveedor, UUID> {

    boolean existsByTelefono(String telefono);

    boolean existsByTelefonoAndIdNot(String telefono, UUID id);

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdNot(String nombre, UUID id);
}