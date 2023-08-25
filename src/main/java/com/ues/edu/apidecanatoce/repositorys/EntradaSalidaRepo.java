package com.ues.edu.apidecanatoce.repositorys;

import com.ues.edu.apidecanatoce.entities.Entrada_Salidas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntradaSalidaRepo extends JpaRepository<Entrada_Salidas, UUID> {

}
