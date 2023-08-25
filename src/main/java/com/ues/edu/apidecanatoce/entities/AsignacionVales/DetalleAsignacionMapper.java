package com.ues.edu.apidecanatoce.entities.AsignacionVales;


import com.ues.edu.apidecanatoce.dtos.AsignacionValesDto.DetalleAsignacionDto;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DetalleAsignacionMapper implements Function<DetalleAsignacionVale, DetalleAsignacionDto> {
    @Override
    public DetalleAsignacionDto apply(DetalleAsignacionVale detalleAsignacionVale) {
        return DetalleAsignacionDto.builder()
                .codigoAsignacionVale(detalleAsignacionVale.getAsignacionVale().getCodigoAsignacion())
                .fechaAsigancion(detalleAsignacionVale.getAsignacionVale().getFecha())
                .codigoVale(detalleAsignacionVale.getVale().getCodigoVale())
                .build();
    }
}
