package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto;

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
    private int estadoVale;

    public Vale toVale() {
        return Vale.builder()
                .id(this.idVale)
                .estado(this.estadoVale)
                .build();
    }
}
