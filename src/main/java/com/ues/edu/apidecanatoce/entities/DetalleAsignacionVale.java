package com.ues.edu.apidecanatoce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_detalle_asignacion_vale")
public class DetalleAsignacionVale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_asignacion_vale")
    private Integer idDetalleAsignacionVale;

    @ManyToOne
    @JoinColumn(name = "id_asignacion_vale", referencedColumnName = "codigo_asignacion")
    private SolicitudVale solicitudVale;
}
