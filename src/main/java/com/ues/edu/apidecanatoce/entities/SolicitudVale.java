package com.ues.edu.apidecanatoce.entities;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_solicitud_vale")
public class SolicitudVale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_vale")
    private Integer idSolicitudVale;

    @OneToMany(mappedBy = "solicitudVale")
    private Set<AsignacionVale> asignacionValeSet;
}
