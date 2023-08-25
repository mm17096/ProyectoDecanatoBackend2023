package com.ues.edu.apidecanatoce.repositorys.vehiculo;

import com.ues.edu.apidecanatoce.entities.vehiculo.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, UUID> {
    boolean existsByPlaca(String placa);

    boolean existsByPlacaAndCodigoVehiculoNot(String placa, UUID codigoVehiculo);



}
