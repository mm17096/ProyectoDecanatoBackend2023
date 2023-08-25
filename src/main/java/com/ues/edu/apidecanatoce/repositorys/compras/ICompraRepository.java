package com.ues.edu.apidecanatoce.repositorys.compras;

import com.ues.edu.apidecanatoce.entities.compras.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICompraRepository extends JpaRepository<Compra, UUID> {

    boolean existsByFactura(String factura);

    boolean existsByFacturaAndIdNot(String factura, UUID id);
}