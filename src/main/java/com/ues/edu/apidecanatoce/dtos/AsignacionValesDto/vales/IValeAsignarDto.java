package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.vales;

import com.ues.edu.apidecanatoce.entities.compras.Vale;
import lombok.*;

import java.util.UUID;

public interface IValeAsignarDto {
    UUID getIdVale();
    Integer getCorrelativoVale();
    Double getValorVale();
}
