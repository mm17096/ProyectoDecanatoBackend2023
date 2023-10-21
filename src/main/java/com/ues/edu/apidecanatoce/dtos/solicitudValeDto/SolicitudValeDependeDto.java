package com.ues.edu.apidecanatoce.dtos.solicitudValeDto;

import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@Builder
public class SolicitudValeDependeDto {
    private UUID idSolicitudVale;
    @NotNull(message = "El valor es obligatorio")
    @Digits(integer = 10, fraction = 2, message = "El valor debe ser un número válido con máximo 10 dígitos en total y 2 decimales")
    @Min(value = 0, message = "El valor debe ser mayor o igual a 0")
    private int cantidadVale;
    @NotNull(message = "El estado es obligatorio")
    @Digits(integer = 10, fraction = 0, message = "El estado debe ser un número válido con máximo 10 dígitos en total y 0 decimales")
    @Min(value = 0, message = "El estado debe ser mayor o igual a 0")
    private int estadoEntrada;
    @NotNull(message = "Solicitud de vehiculo es obligatoria")
    private SolicitudVehiculo solicitudVehiculo;
    public SolicitudVale toEntitySaveDep() {
        return SolicitudVale.builder().idSolicitudVale(this.idSolicitudVale).cantidadVale(this.cantidadVale)
                .estadoEntrada(this.estadoEntrada).solicitudVehiculo(this.solicitudVehiculo).build();
    }
}
