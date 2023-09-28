package com.ues.edu.apidecanatoce.repositorys.compras;

import com.ues.edu.apidecanatoce.entities.compras.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ICompraRepository extends JpaRepository<Compra, UUID> {

    boolean existsByFactura(String factura);

    boolean existsByFacturaAndIdNot(String factura, UUID id);

    List<Compra> findByFechaCompraBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}