package com.ues.edu.apidecanatoce.dtos.asignacionValesDto.vales;

import com.ues.edu.apidecanatoce.entities.compras.Vale;
import lombok.*;

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

    public Vale valeDto() {
        return Vale.builder()
                .id(this.idVale)
                .correlativo(this.correlativo)
                .estado(this.estadoVale)
                .build();
    }
}
