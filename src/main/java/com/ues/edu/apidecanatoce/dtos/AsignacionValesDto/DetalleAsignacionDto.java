package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DetalleAsignacionDto {
    private String codigoAsignacionVale;
    private LocalDate fechaAsigancion;
    private Long codigoVale;
}
