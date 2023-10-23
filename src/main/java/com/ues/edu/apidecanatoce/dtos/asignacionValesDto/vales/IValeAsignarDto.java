package com.ues.edu.apidecanatoce.dtos.asignacionValesDto.vales;

import java.util.UUID;

public interface IValeAsignarDto {
    UUID getIdVale();
    Integer getCorrelativoVale();
    Double getValorVale();
    String getFechaVencimiento();
}
