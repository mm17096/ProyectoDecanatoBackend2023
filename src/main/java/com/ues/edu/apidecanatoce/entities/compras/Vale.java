package com.ues.edu.apidecanatoce.entities.compras;

import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.ValeDto;
import com.ues.edu.apidecanatoce.dtos.compras.ValeDependeDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tb_vale")
public class Vale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_vale")
    private UUID id;

    @Column(name ="codigo_vale", unique = true)
    private long codigoVale;

    @Column(name = "estado")
    private int estado;

    @Column(name = "valor")
    private double valor;

    @ManyToOne
    @JoinColumn(name="codigo_compra",nullable=false,
            foreignKey=@ForeignKey(name="FK-compra"))
    private Compra compra;

    @Column(name ="correlativo")
    private long correlativo;

    public ValeDependeDto toDTO() {
        return ValeDependeDto.builder().id(this.id).codigoVale(this.codigoVale).estado(this.estado).valor(this.valor).compra(this.compra.toDepDTO())
                .correlativo(this.correlativo).build();
    }
    public ValeDto toValeDto() {
        return ValeDto.builder()
                .idVale(this.id)
                .codigoVale(this.codigoVale)
                .build();
    }


}
