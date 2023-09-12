package com.ues.edu.apidecanatoce.entities.solicitudVale;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.SolicitudValeModDto;
import com.ues.edu.apidecanatoce.dtos.SolicitudVvDTO;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.SolicitudValeModDto;
import com.ues.edu.apidecanatoce.dtos.compras.ValeDependeDto;
import com.ues.edu.apidecanatoce.dtos.documentovaleDto.SolicitudvaleDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.SolicitudValeDependeDto;
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

    @Column(name = "estado", nullable = false)
    private int estado;

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

    public SolicitudValeDependeDto toDTOt() {
        return SolicitudValeDependeDto.builder().idSolicitudVale(this.idSolicitudVale).cantidadVale(this.cantidadVale)
                .estadoEntrada(this.estadoEntrada).solicitudVehiculo(this.solicitudVehiculo.toDepDTO()).build();
    }

    public SolicitudVvDTO tosolicitudVvDTO() {
        return SolicitudVvDTO.builder()
                .idSolicitudVale(this.idSolicitudVale)
                .codigoSolicitudVehiculo(this.solicitudVehiculo.getCodigoSolicitudVehiculo())
                .fechaSolicitud(this.solicitudVehiculo.getFechaSolicitud())
                .fechaSalida(this.solicitudVehiculo.getFechaSalida())
                .unidadSolicitante(this.solicitudVehiculo.getUnidadSolicitante())
                .vehiculo(this.solicitudVehiculo.getVehiculo())
                .objetivoMision(this.solicitudVehiculo.getObjetivoMision())
                .lugarMision(this.solicitudVehiculo.getLugarMision())
                .direccion(this.solicitudVehiculo.getDireccion())
                .horaEntrada(this.solicitudVehiculo.getHoraEntrada())
                .horaSalida(this.solicitudVehiculo.getHoraSalida())
                .cantidadPersonas(this.solicitudVehiculo.getCantidadPersonas())
                .solicitante(this.solicitudVehiculo.getUsuario())
                .nombreJefeDepto(this.solicitudVehiculo.getJefeDepto())
                .fechaEntrada(this.solicitudVehiculo.getFechaEntrada())
                .estado(this.solicitudVehiculo.getEstado())
                .motorista(this.solicitudVehiculo.getMotorista())
                .build();
    }

    public SolicitudValeModDto toSolicitudValeModDto(){
        return SolicitudValeModDto.builder()
                .idSolicitudVale(this.idSolicitudVale)
                .estadoSolicutudVale(this.estadoEntrada)
                .build();
    }
}
