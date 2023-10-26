package com.ues.edu.apidecanatoce.dtos.asignacionValesDto.vales;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class LiquidarValesDto {
    private UUID idAsignacionVale;
    private List<UUID> valesLiquidar;
}
