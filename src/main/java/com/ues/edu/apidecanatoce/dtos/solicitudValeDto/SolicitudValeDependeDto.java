package com.ues.edu.apidecanatoce.dtos.solicitudValeDto;

import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@Builder
public class SolicitudValeDependeDto {
    private UUID idSolicitudVale;
    private int cantidadVale;
    private int estadoEntrada;
    private SolicitudVehiculo solicitudVehiculo;
    public SolicitudVale toEntitySaveDep() {
        return SolicitudVale.builder().idSolicitudVale(this.idSolicitudVale).cantidadVale(this.cantidadVale)
                .estadoEntrada(this.estadoEntrada).solicitudVehiculo(this.solicitudVehiculo).build();
    }
}
