package com.ues.edu.apidecanatoce.entities.AsignacionVales;

import com.ues.edu.apidecanatoce.entities.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_detalle_asignacion_vale")
public class DetalleAsignacionVale {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_asignacion_vale")
    private String idDetalleAsignacionVale;

    @ManyToOne
    @JoinColumn(name = "id_asignacion_vale", referencedColumnName = "codigo_asignacion")
    private AsignacionVale asignacionVale;

    @ManyToOne
    @JoinColumn(name = "valeid", referencedColumnName = "id_vale")
    private Vale vale;
}
