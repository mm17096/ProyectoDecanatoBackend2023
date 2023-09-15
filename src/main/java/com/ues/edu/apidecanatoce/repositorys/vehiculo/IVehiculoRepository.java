package com.ues.edu.apidecanatoce.repositorys.vehiculo;

import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, UUID> {
    boolean existsByPlacaIgnoreCase(String placa);

    boolean existsByPlacaAndCodigoVehiculoNot(String placa, UUID codigoVehiculo);

    List<Vehiculo> findByClaseIgnoreCase(String clase);

    List<Vehiculo> findByPlacaIgnoreCase(String mio);
}
