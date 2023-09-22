package com.ues.edu.apidecanatoce.dtos.compras;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ActualizacionValesRequestDto {
    private List<ValeDependeDto> vales;
    private String concepto;
    private String idusuariologueado;
}
