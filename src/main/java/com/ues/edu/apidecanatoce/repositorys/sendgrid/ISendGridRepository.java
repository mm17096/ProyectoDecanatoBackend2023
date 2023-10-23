package com.ues.edu.apidecanatoce.repositorys.sendgrid;

import com.ues.edu.apidecanatoce.entities.sendgrid.Sendgrid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISendGridRepository extends JpaRepository<Sendgrid, UUID> {

}
