package com.ues.edu.apidecanatoce.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ues.edu.apidecanatoce.dtos.compras.ProveedorDto;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.SolicitudvaleDto;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "tb_solicitud_vale")
public class SolicitudVale {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_solicitud_vale")
    private UUID idSolicitudVale;

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

    public SolicitudvaleDto toDTO() {
        return SolicitudvaleDto.builder().id(this.idSolicitudVale).cantidadVale(this.cantidadVale).estadoEntrada(this.estadoEntrada).build();
    }

}
