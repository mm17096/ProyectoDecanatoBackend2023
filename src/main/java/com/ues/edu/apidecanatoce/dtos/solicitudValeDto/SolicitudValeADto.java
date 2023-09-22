package com.ues.edu.apidecanatoce.dtos.solicitudValeDto;

import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import com.ues.edu.apidecanatoce.entities.solicitudVehiculo.SolicitudVehiculo;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.solicitudVehiculo.ISolicitudVehiculoRepository;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Getter
@Setter
@Builder
public class SolicitudValeADto {

    private UUID idSolicitudVale;

    private Integer cantidadVale;

    private Integer estadoEntrada;

    private Integer estado;

    @NotNull(message = "Solicitud de vehiculo es obligatoria")
    private UUID solicitudVehiculo;

    public SolicitudVale toEntityComplete(ISolicitudVehiculoRepository SolicitudVehiculo) {
        SolicitudVehiculo solicitudVehiculobuscar = SolicitudVehiculo.findById(this.solicitudVehiculo).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentro compra"));
        return SolicitudVale.builder().idSolicitudVale(this.idSolicitudVale).cantidadVale(this.cantidadVale)
                .estadoEntrada(this.estadoEntrada).solicitudVehiculo(solicitudVehiculobuscar).estado(this.estado).
                build();
    }
}
