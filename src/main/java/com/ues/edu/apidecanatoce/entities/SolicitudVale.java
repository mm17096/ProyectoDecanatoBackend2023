package com.ues.edu.apidecanatoce.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "tb_solicitud_vale")
public class SolicitudVale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_vale")
    private Integer idSolicitudVale;

    @Column(name = "cantidad_vale", nullable = false)
    private int cantidadVale;

    @Column(name = "estado_entrada", nullable = false)
    private int estadoEntrada;

   // @OneToMany(mappedBy = "solicitudVale")
  //  private Set<AsignacionVale> asignacionValeSet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "solicitud_vehiculo_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JsonManagedReference
    private SolicitudVehiculo solicitudVehiculo;

   // @OneToMany(mappedBy = "solicitudVale")
   // private Set<AsignacionVale> asignacionValeSet;

}
