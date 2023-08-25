package com.ues.edu.apidecanatoce.repositorys;

import com.ues.edu.apidecanatoce.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Integer> {

}
