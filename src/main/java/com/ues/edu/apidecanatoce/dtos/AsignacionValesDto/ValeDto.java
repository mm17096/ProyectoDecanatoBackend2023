package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto;

import com.ues.edu.apidecanatoce.entities.compras.Vale;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ValeDto {

    private UUID idVale;
    private long codigoVale;

    public Vale ValeDto() {
        return Vale.builder()
                .id(this.idVale)
                .codigoVale(this.codigoVale)
                .build();
    }

}
