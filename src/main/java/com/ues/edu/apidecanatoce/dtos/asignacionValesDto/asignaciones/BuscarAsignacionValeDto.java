package com.ues.edu.apidecanatoce.dtos.asignacionValesDto.asignaciones;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BuscarAsignacionValeDto {

        private UUID codigoAsignacion;
}
