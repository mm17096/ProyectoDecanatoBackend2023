package com.ues.edu.apidecanatoce.dtos.compras;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioMandarDto {
    String nombre;
    String clave;
}
