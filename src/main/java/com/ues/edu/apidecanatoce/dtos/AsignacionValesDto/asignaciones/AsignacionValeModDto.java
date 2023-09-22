package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.asignaciones;

import com.ues.edu.apidecanatoce.entities.AsignacionVales.AsignacionVale;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class AsignacionValeModDto {
    private UUID idAsignacionVale;
    private int estadoAsignacion;

    public AsignacionVale toEntity(){
        return AsignacionVale.builder()
                .codigoAsignacion(this.idAsignacionVale)
                .estado(this.estadoAsignacion)
                .build();
    }
}
