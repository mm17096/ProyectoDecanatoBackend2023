package com.ues.edu.apidecanatoce.dtos.compras;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ActualizacionValesRequestDto {
    private List<ValeDependeDto> vales;
    private UUID idProveedor;
    private String concepto;
}
