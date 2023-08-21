package com.ues.edu.apidecanatoce.dtos;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AsignacionValeDTO implements Serializable {
    private Integer codigoAsignacion;
    private Integer estadoAsignacion;
    private String fechaAsignacion;
}
