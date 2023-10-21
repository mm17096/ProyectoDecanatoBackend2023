package com.ues.edu.apidecanatoce.entities.compras;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.dtos.asignacionValesDto.vales.ValeModDto;
import com.ues.edu.apidecanatoce.dtos.compras.ValeDependeDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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

    @Column(name = "estado")
    private int estado;

    @Column(name = "valor")
    private double valor;

    @ManyToOne
    @JoinColumn(name="codigo_compra",nullable=false,
            foreignKey=@ForeignKey(name="FK-compra"))
    private Compra compra;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @Column(name ="correlativo")
    private long correlativo;

    public ValeDependeDto toDTO() {
        return ValeDependeDto.builder().id(this.id).estado(this.estado).valor(this.valor).compra(this.compra.toDepDTO())
                .fechaVencimiento(this.fechaVencimiento).correlativo(this.correlativo).build();
    }
    public ValeModDto toValeModDto() {
        return ValeModDto.builder()
                .idVale(this.id)
                .estadoVale(this.estado)
                .correlativo(this.correlativo)
                .build();                

    }


}
