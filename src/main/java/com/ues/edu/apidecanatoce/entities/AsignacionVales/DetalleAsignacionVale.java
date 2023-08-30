package com.ues.edu.apidecanatoce.entities.AsignacionVales;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.DetalleAsignacionDto;
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
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
