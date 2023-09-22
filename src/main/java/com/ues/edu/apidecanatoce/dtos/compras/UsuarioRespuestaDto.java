package com.ues.edu.apidecanatoce.dtos.compras;

import com.ues.edu.apidecanatoce.entities.empleado.Empleado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRespuestaDto {
    String codigoUsuario;
    Empleado empleado;
}
