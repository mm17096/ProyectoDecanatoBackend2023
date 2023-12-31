package com.ues.edu.apidecanatoce.repositorys.cargo;

import com.ues.edu.apidecanatoce.entities.cargos.Cargo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface ICargoRepository extends JpaRepository<Cargo, UUID> {

    /*
    @Query(value = "SELECT \n" +
            "tb_cargo.nombre_cargo as nombreCargo,\n" +
            "tb_cargo.descripcion as descripcion,\n" +
            "tb_estados.nombre_estado as nomEstado\n" +
            "FROM \"tb_cargo\"\n" +
            "INNER JOIN tb_estados on tb_cargo.estado = tb_estados.codigo_estado \n" +
            "WHERE tb_cargo.estado= :statusparam", nativeQuery = true)
    List<ICargoxEstadoDTO> findCargoByEstado(@Param("statusparam") Integer statusparam);
*/

    List<Cargo> findAllByEstado(int estado);

    List<Cargo> findAllByNombreCargo(String estado);

    boolean existsByNombreCargo(String nombreCargo);

    boolean existsByNombreCargoAndIdNot(String nombreCargo, UUID id);

}
