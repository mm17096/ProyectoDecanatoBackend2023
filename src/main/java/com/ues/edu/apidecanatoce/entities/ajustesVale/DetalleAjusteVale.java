package com.ues.edu.apidecanatoce.entities.ajustesVale;

import com.ues.edu.apidecanatoce.dtos.ajusteVale.AjusteValeDto;
import com.ues.edu.apidecanatoce.dtos.ajusteVale.DetalleAjusteValeDto;
import com.ues.edu.apidecanatoce.entities.compras.Vale;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_detalle_ajuste_vale")

public class DetalleAjusteVale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_detalle_ajuste")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_vale", nullable = false,
            foreignKey = @ForeignKey(name = "FK-vale"))
    private Vale vale;

    @ManyToOne
    @JoinColumn(name = "codigo_ajuste", nullable = false,
            foreignKey = @ForeignKey(name = "FK-ajuste"))
    private AjusteVale ajusteVale;

    public DetalleAjusteValeDto toDTO() {
        return DetalleAjusteValeDto.builder().id(this.id).vale(this.vale.getId())
                .ajusteVale(this.ajusteVale.getId()).build();
    }
}
