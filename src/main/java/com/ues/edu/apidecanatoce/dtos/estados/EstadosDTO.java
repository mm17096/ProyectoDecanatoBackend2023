package com.ues.edu.apidecanatoce.dtos.estados;

import com.ues.edu.apidecanatoce.entities.Estados;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EstadosDTO {
    private Integer codigoEstado;
    private String nombreEstado;

    public Estados toEntityComplete() {
        return Estados.builder().codigoEstado(this.codigoEstado).nombreEstado(this.nombreEstado)
                .build();
    }
}
