package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.vales;

import com.ues.edu.apidecanatoce.entities.compras.Vale;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ValeModDto {
    private UUID idVale;
    private Long correlativo;
    private int estadoVale;
    private LocalDate fechaVencimiento;

    public Vale valeDto() {
        return Vale.builder()
                .id(this.idVale)
                .correlativo(this.correlativo)
                .estado(this.estadoVale)
                .fechaVencimiento(this.fechaVencimiento)
                .build();
    }
}
