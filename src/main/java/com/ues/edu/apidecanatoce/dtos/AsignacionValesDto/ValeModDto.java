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
    private long codigoVale;
    private int estadoVale;

    public Vale valeDto() {
        return Vale.builder()
                .id(this.idVale)
                .codigoVale(this.codigoVale)
                .estado(this.estadoVale)
                .build();
    }
}
