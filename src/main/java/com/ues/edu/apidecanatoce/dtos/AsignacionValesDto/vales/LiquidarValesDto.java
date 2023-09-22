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
public class LiquidarValesDto {
    private UUID idAsignacionVale;
    private List<UUID> valesLiquidar;
}
