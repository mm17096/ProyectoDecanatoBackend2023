package com.ues.edu.apidecanatoce.dtos.documentovaleDto;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder

public class SolicitudvaleDto {

    private UUID id;

    @NotBlank(message = "La cantidad es bligatoria")
    private int cantidadVale;

    @NotBlank(message = "El estado es bligatorio")
    private int estadoEntrada;

    @NotNull(message = "codigo de solicitud vehiculo es obligatorio")
    private UUID solicitudVehiculo;

    public SolicitudVale toEntityComplete() {
        return SolicitudVale.builder().idSolicitudVale(this.id).cantidadVale(this.cantidadVale).estadoEntrada(this.estadoEntrada).build();
    }

}
