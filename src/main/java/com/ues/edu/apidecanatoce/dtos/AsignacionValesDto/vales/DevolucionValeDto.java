package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.vales;

import com.ues.edu.apidecanatoce.entities.compras.Vale;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class DevolucionValeDto {
    private List<UUID> valesDevueltos;
    private Integer estadoVales;

/*
    public Vale toEntity(List<UUID> listValesDevueltos, Integer estadoVale){
        this.valesDevueltos = listValesDevueltos;
        for (UUID idVale: valesDevueltos) {
            return Vale.builder()
                    .id(idVale)
                    .estado(estadoVale)
                    .build();
        }
        return Vale.builder().build();
    }*/
}
