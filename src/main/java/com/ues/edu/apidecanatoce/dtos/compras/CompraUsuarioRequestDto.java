package com.ues.edu.apidecanatoce.dtos.compras;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CompraUsuarioRequestDto {
    private CompraInsertarDto compra;
    private String idusuariologueado;
}
