package com.ues.edu.apidecanatoce.repositorys;

import com.ues.edu.apidecanatoce.entities.Cargo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICargoRepository extends JpaRepository<Cargo,Integer> {
}
