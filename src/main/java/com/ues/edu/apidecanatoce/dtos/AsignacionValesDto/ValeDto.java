package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto;

import com.ues.edu.apidecanatoce.entities.Vale;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ValeDto {

    private String idVale;
    private long codigoVale;

    public Vale ValeDto() {
        return Vale.builder()
                .idVale(this.idVale)
                .codigoVale(this.codigoVale)
                .build();
    }

}
