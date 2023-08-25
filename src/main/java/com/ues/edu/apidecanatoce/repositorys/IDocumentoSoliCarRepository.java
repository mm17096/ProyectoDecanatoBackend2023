package com.ues.edu.apidecanatoce.repositorys;

import com.ues.edu.apidecanatoce.entities.DocumentoSoliCar;
import com.ues.edu.apidecanatoce.entities.Documentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentoSoliCarRepository extends JpaRepository<DocumentoSoliCar,Integer> {

}
