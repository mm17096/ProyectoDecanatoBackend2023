package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BuscarSolicitudValeDto {

    private UUID codigoSolicitudVale;
}
