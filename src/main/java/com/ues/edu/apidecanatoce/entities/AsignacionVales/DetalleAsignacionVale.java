package com.ues.edu.apidecanatoce.entities.AsignacionVales;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.detalles.DetalleAsignacionDto;
import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.detalles.DetalleAsignacionInDto;
import com.ues.edu.apidecanatoce.dtos.solicitudValeDto.ConsultaValeDto;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_detalle_asignacion_vale")
@Builder
public class DetalleAsignacionVale {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_detalle_asignacion_vale")
    private UUID idDetalleAsignacionVale;

    @ManyToOne
    @JoinColumn(name = "id_asignacion_vale", referencedColumnName = "codigo_asignacion")
    private AsignacionVale asignacionVale;

    @ManyToOne
    @JoinColumn(name = "valeid", referencedColumnName = "id_vale")
    private Vale vale;

    public DetalleAsignacionDto toDTODetalle() {
        return DetalleAsignacionDto.builder().idDetalleAsignacionVale(this.idDetalleAsignacionVale)
                .codigoAsignacionVale(this.asignacionVale.getCodigoAsignacion())
                .Vale(this.vale)
                .build();
    }

    public ConsultaValeDto toDTODetalleC() {
        return ConsultaValeDto.builder().idDetalleAsignacionVale(this.idDetalleAsignacionVale)
                .asignacionVale(this.asignacionVale.getCodigoAsignacion())
                .valeid(this.vale.getId()).vale(this.vale).estado(this.asignacionVale.getEstado())
                .fecha(this.asignacionVale.getFecha()).solicitudVale(this.asignacionVale.getSolicitudVale())
                .build();
    }

    public DetalleAsignacionInDto toDto() {
        return DetalleAsignacionInDto.builder().idDetalleAsignacionVale(this.idDetalleAsignacionVale)
                .codigoAsignacionVale(this.asignacionVale.getCodigoAsignacion())
                .idVale(this.vale.getId())
                .build();
    }

}
