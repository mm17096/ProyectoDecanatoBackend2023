package com.ues.edu.apidecanatoce.repositorys.entradaSalida;

import com.ues.edu.apidecanatoce.entities.entradaSalida.Entrada_Salidas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EntradasalidaRepository extends JpaRepository<Entrada_Salidas, UUID> {

}
