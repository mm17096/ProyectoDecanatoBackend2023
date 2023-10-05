package com.ues.edu.apidecanatoce.repositorys.solicitudVale;

import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaCompraDto;
import com.ues.edu.apidecanatoce.entities.compras.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface IConsultaCompraRepository extends JpaRepository<Compra, UUID> {
    @Query(value = "SELECT c.codigo_compra AS codigocompra, c.cantidad AS cantidad, c.codigo_fin AS codigofin, c.codigo_inicio AS codigoinicio, CAST(c.fecha_compra AS DATE) AS fechacompra, c.fecha_vencimiento AS fechavencimientovale, precio_unitario AS preciounitario FROM tb_compra c WHERE CAST(c.fecha_compra AS DATE) >=:fechaI AND CAST(c.fecha_compra AS DATE) <=:fechaF ORDER BY CAST(c.fecha_compra AS DATE), c.cantidad DESC", nativeQuery = true)
    List<ConsultaCompraDto> listarCompraDeVales(LocalDate fechaI, LocalDate fechaF);
}
