package com.ues.edu.apidecanatoce.dtos.asignacionValesDto.asignaciones;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.asignacionVales.AsignacionVale;
import com.ues.edu.apidecanatoce.entities.solicitudVale.SolicitudVale;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AsignacionValeDto {
    private UUID codigoAsignacion;

    @NotBlank(message = "Ingrese el estado de la asignacion")
    @NotNull(message = "El estado de la asignacion no puede ir vacío")
    @Digits(integer = 10, fraction = 0)
    private int estadoAsignacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDate fechaAsignacion;
    private SolicitudVale solicitudVale;

    public AsignacionVale toAsignacionValeSave(){
        return AsignacionVale.builder()
                .codigoAsignacion(this.codigoAsignacion)
                .estado(this.estadoAsignacion)
                .fecha(this.fechaAsignacion)
                .solicitudVale(this.solicitudVale)
                .build();
    }

}
