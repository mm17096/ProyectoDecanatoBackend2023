package com.ues.edu.apidecanatoce.dtos.AsignacionValesDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.edu.apidecanatoce.entities.AsignacionVales.AsignacionVale;
import com.ues.edu.apidecanatoce.entities.SolicitudVale;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import com.ues.edu.apidecanatoce.repositorys.asignacionvale.ISolicitudValeRepository;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AsignacionValeInDto {
    private UUID codigoAsignacion;

    @NotBlank(message = "Ingrese el estado de la asignacion")
    @NotNull(message = "El estado de la asignacion no puede ir vacío")
    @Digits(integer = 10, fraction = 0)
    private int estadoAsignacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDate fechaAsignacion;
    private UUID solicitudVale;

    public AsignacionVale toAsignacionValeComplete(ISolicitudValeRepository iSolicitudValeRepository){
        SolicitudVale solicitudValeById = iSolicitudValeRepository.findById(solicitudVale).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "No se encuentra la solicitud"));
        return AsignacionVale.builder()
                .codigoAsignacion(this.codigoAsignacion)
                .estado(this.estadoAsignacion)
                .fecha(this.fechaAsignacion)
                .solicitudVale(solicitudValeById)
                .build();
    }
}
