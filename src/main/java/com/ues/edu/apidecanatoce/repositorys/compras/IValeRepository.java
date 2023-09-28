package com.ues.edu.apidecanatoce.repositorys.compras;

import com.ues.edu.apidecanatoce.entities.compras.Vale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IValeRepository extends JpaRepository<Vale, UUID> {
    // Devulve de vales por activos ordenados
    @Query("SELECT v FROM Vale v JOIN v.compra c WHERE v.estado = 8 ORDER BY c.fechaCompra, v.correlativo ASC")
    List<Vale> findValesActivosOrderByFechaCompraCorrelativo();

    default List<Vale> findValesActivosOrderByFechaCompraCorrelativoWithLimit(int cantidad) {
        List<Vale> valesActivos = findValesActivosOrderByFechaCompraCorrelativo();

        if (cantidad <= valesActivos.size()) {
            return valesActivos.subList(0, cantidad);
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No existe esa cantidad de vales disponibles");
        }
    }
}